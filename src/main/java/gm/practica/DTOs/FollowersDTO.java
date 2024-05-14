package gm.practica.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FollowersDTO {
    Long follower;
    Long followed;
    Boolean active;
}
