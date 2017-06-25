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
    private List<T> list;
    private int pageNumber;
    private int pageSize;
    private int totalPage;
    private int totalRow;

    public PageEntity(List<T> list, int pageNumber, int pageSize, int totalRow) {
        this.list = list;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalPage = (totalRow - 1) / pageSize + 1;
        this.totalRow = totalRow;
    }

    public boolean isFirstPage() {
        return this.pageNumber == 1;
    }

    public boolean isLastPage() {
        return this.pageNumber >= this.totalPage;
    }
}
