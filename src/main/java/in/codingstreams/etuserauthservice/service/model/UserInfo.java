package in.codingstreams.etuserauthservice.service.model;

import java.time.LocalDateTime;

public interface UserInfo {
  String getName();
  String getEmail();
  LocalDateTime getCreatedAt();
}
