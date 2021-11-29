package br.com.fatec.apibackend.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
  @PostMapping("/{id}")
  public void tracerouter(@PathVariable long id, @RequestBody Score score) {
    Optional<Usuario> user = userRepo.findById(id);
    score.setUsuario(user.get());
    scoreRepo.save(score);
  }


}
