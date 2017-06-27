package Entity;

import com.jfinal.plugin.activerecord.Page;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Administrator on 2017/6/25.
 */
@Data
@NoArgsConstructor
public class PageEntity<T> extends Page<T>{

    public PageEntity(List<T> list, int pageNumber, int pageSize, int totalRow) {
        super(list,pageNumber,pageSize,(totalRow - 1) / pageSize + 1,totalRow);
    }
}
