package top.arexstorm.sharing.bean.info;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Information implements Serializable {
    private Long id;

    private String informationid;

    private String name;

    private String shortname;

    private String userid;

    private String pic;

    private String typeid;

    private String typename;

    private String content;

    private String summary;

    private BigDecimal price;

    private Short important;

    private Short status;

    private Date createtime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInformationid() {
        return informationid;
    }

    public void setInformationid(String informationid) {
        this.informationid = informationid == null ? null : informationid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname == null ? null : shortname.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid == null ? null : typeid.trim();
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename == null ? null : typename.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Short getImportant() {
        return important;
    }

    public void setImportant(Short important) {
        this.important = important;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", informationid=").append(informationid);
        sb.append(", name=").append(name);
        sb.append(", shortname=").append(shortname);
        sb.append(", userid=").append(userid);
        sb.append(", pic=").append(pic);
        sb.append(", typeid=").append(typeid);
        sb.append(", typename=").append(typename);
        sb.append(", content=").append(content);
        sb.append(", summary=").append(summary);
        sb.append(", price=").append(price);
        sb.append(", important=").append(important);
        sb.append(", status=").append(status);
        sb.append(", createtime=").append(createtime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}