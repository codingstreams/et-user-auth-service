package in.codingstreams.etuserauthservice.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {
  private String name;
  private String email;
  private String password;
  private String requestType;
}
