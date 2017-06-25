package Entity;

import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2017/6/24.
 */
@Data
public class CodeEntity {
    private String title;
    private String code;
    private List<String> tags;
    private String language;
    private String value;
}
