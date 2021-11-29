package br.com.fatec.apibackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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

  @CrossOrigin
  @JsonView(ViewUsuario.UsuarioView.class)
  @PostMapping("/{id}")
  public ResponseEntity<Score> cadastra(@PathVariable long id, @RequestBody Score score) {
    System.out.println(score.getCluster());
    Usuario user = userRepo.findById(id).get();
    score.setUsuario(user);
    Score scoreNew = scoreRepo.save(score);
    user.setScore(scoreNew);
    userRepo.save(user);
    return ResponseEntity.ok(score);
  }


  @CrossOrigin
  @JsonView(ViewUsuario.UsuarioView.class)
  @DeleteMapping("/{id}")
  public ResponseEntity<Score> deletaScore(@PathVariable long id) {
    scoreRepo.deleteById(id);
    return ResponseEntity.ok().build();
  }

}
