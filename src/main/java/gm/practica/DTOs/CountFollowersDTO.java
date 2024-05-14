package gm.practica.DTOs;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CountFollowersDTO {
    private Long userId;
    private String userName;
    private Integer followersCount;
}
