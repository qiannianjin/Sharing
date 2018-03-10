package top.arexstorm.sharing.bean.file;

import java.io.Serializable;
import java.util.Date;

public class CustomerPicPath extends PicPath {
    
    private static final long serialVersionUID = 1L;
    
    private Integer pageNo = 1;
    private Integer pageSize = 10;
    private Integer startRow;
    
	public void setPageNo(Integer pageNo) {
		this.startRow = (pageNo-1) * pageSize;
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.startRow = (this.pageNo-1) * pageSize;
		this.pageSize = pageSize;
	}

	public Integer getStartRow() {
		return startRow;
	}

	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}
}