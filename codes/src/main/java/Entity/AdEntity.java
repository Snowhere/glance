package Entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * advertisement
 *
 * @author STH
 * @create 2017-06-29
 **/
@Data
@AllArgsConstructor
public class AdEntity {
    private String link;
    private String alt;
    private String img;
}
