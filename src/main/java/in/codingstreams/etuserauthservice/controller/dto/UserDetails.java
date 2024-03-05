package in.codingstreams.etuserauthservice.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetails {
  private String name;
  private String email;
  private UpdateRequestType requestType;
}
