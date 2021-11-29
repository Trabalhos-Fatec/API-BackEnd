package br.com.fatec.apibackend.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.annotation.JsonView;
import br.com.fatec.apibackend.entities.Score;
import br.com.fatec.apibackend.entities.Usuario;
import br.com.fatec.apibackend.repository.ScoreRepository;
import br.com.fatec.apibackend.repository.UserRepository;
import br.com.fatec.apibackend.views.ViewUsuario;

@RestController
@CrossOrigin
@RequestMapping("/score")
public class ScoreController {

  @Autowired
  private UserRepository userRepo;

  @Autowired
  private ScoreRepository scoreRepo;

  @JsonView(ViewUsuario.UsuarioView.class)
  @RequestMapping("/{id}")
  public void cadastra(@PathVariable long id, @RequestBody String cluster,
      @RequestBody String fingerPrint, @RequestBody String traceRouter) {
    Score score = new Score();
    score.setCluster(Integer.parseInt(cluster));
    score.setFingerPrint(fingerPrint);
    score.setTraceRouter(traceRouter);
    Optional<Usuario> user = userRepo.findById(id);
    score.setUsuario(user.get());
    scoreRepo.save(score);
  }

  @JsonView(ViewUsuario.UsuarioView.class)
  @DeleteMapping("/{id}")
  public void deletaScore(@PathVariable long id) {
    scoreRepo.deleteById(id);
  }

}
