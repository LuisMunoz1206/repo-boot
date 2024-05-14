package gm.practica.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FollowersCountDTO {
    private Long user_id;
    private String user_name;
    private Integer followers_count;
}