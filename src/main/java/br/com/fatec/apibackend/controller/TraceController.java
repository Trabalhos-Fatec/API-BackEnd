package br.com.fatec.apibackend.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.fatec.apibackend.entities.Usuario;
import br.com.fatec.apibackend.repository.CustomTraceRepository;
import br.com.fatec.apibackend.repository.UserRepository;
import br.com.fatec.apibackend.services.UserService;

@RestController
@CrossOrigin
@RequestMapping("/tracerouter")
public class TraceController {
  @Autowired
  public UserService userServ;

  @Autowired
  private UserRepository userRepo;

  @Autowired
  CustomTraceRepository trace;


  @GetMapping("/chaining")
  public List<HttpTrace> chaining() {
    return trace.findAll();
  }

  @PostMapping("/tracerouter/{IP}")
  public void tracerouter(@PathVariable String IP, @RequestBody Usuario usuario) {
    List<Usuario> user = userRepo.findByDadosEmailEmail(usuario.getEmail(0));
    user.get(0).setTraceRouter(traceRoute(IP));
    userRepo.save(user.get(0));
  }

  public static String runSystemCommand(String command) {
    String s = "";
    String resp = "";
    try {
      Process p = Runtime.getRuntime().exec(command);
      BufferedReader inputStream = new BufferedReader(new InputStreamReader(p.getInputStream()));
      while ((s = inputStream.readLine()) != null) {
        resp += (s + " &");
      }
    } catch (Exception e) {
      System.out.println(e + " Davi deus");
    }
    String[] textoSeparado = resp.split("& &");
    resp = (textoSeparado[1].replaceAll("Esgotado o tempo limite do pedido.", "null"));
    System.out.println(resp);
    return resp;
  }

  public String traceRoute(String IP) {
    String route = "";
    try {
      String host = IP;
      int timeout = 3000;
      boolean status = InetAddress.getByName(host).isReachable(timeout);
      System.out.println(host + ": reachable? " + status);
      route = (host + ": reachable? " + status);
    } catch (java.net.UnknownHostException e) {
      e.printStackTrace();
    } catch (java.io.IOException ioe) {
      ioe.printStackTrace();
    }
    return route;
  }

}
