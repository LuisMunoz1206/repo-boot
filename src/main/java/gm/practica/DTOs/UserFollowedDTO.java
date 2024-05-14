package gm.practica.DTOs;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserFollowedDTO {
    private Long userId;
    private String userName;
    private List<FollowedListDTO> followed;

}
