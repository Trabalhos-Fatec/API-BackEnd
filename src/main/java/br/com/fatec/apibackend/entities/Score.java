package br.com.fatec.apibackend.entities;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonView;
import br.com.fatec.apibackend.views.ViewUsuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "score")
public class Score {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(fetch = FetchType.LAZY, mappedBy = "score")
  private Usuario usuario;

  @JsonView(ViewUsuario.UsuarioCompletoView.class)
  @Column(name = "trace_router", length = 10485760)
  private String traceRouter;

  @JsonView(ViewUsuario.UsuarioCompletoView.class)
  @Column(name = "cluster")
  private int cluster;

  @JsonView(ViewUsuario.UsuarioCompletoView.class)
  @Column(name = "finger_print", length = 10485760)
  private String fingerPrint;

}
