package cn.com.bsfit.namelist.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

public class Namelist implements cn.com.bsfit.obj.Traceable {
    private static Logger log = LoggerFactory.getLogger(Namelist.class);
    private static HashMap<String, Object> mmap = new HashMap<>();

    static {
        try {
            Class<?> aClass = Class.forName("cn.com.bsfit.fdl.function.DefaultGlobalFunction");
            for (Method m : aClass.getDeclaredMethods()) {
                if (Modifier.isStatic(m.getModifiers()) && Modifier.isPublic(m.getModifiers())) {
                    mmap.put(m.getName(), m);
                }
            }
        } catch (ClassNotFoundException e) {
            log.error("找不到全局方法类");
        }
    }

    private transient HashMap<String, Object> cacheMap = new HashMap<>();
    private String traceID;

    public void setTraceID(String traceID) {
        this.traceID = traceID;
    }

    @Override
    public String getTraceID() {
        return traceID;
    }

    private String namelistValue;

    public String getNamelistValue() {
        return this.namelistValue;
    }

    public void setNamelistValue(String namelistValue) {
        this.namelistValue = namelistValue;
    }

    private Integer namelistStatus;

    public Integer getNamelistStatus() {
        return this.namelistStatus;
    }

    public void setNamelistStatus(Integer namelistStatus) {
        this.namelistStatus = namelistStatus;
    }

    private String createBy;

    public String getCreateBy() {
        return this.createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    private Integer typeWay;

    public Integer getTypeWay() {
        return this.typeWay;
    }

    public void setTypeWay(Integer typeWay) {
        this.typeWay = typeWay;
    }

    private Date expireTime;

    public Date getExpireTime() {
        return this.expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    private String namelistType;

    public String getNamelistType() {
        return this.namelistType;
    }

    public void setNamelistType(String namelistType) {
        this.namelistType = namelistType;
    }

    private String namelistDim;

    public String getNamelistDim() {
        return this.namelistDim;
    }

    public void setNamelistDim(String namelistDim) {
        this.namelistDim = namelistDim;
    }

    private Date createTime;

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    private String updateBy;

    public String getUpdateBy() {
        return this.updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    private String immuneRule;

    public String getImmuneRule() {
        return this.immuneRule;
    }

    public void setImmuneRule(String immuneRule) {
        this.immuneRule = immuneRule;
    }

    private Date updateTime;

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    private Long namelistId;

    public Long getNamelistId() {
        return this.namelistId;
    }

    public void setNamelistId(Long namelistId) {
        this.namelistId = namelistId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        Namelist other = (Namelist) o;
        return Objects.equals(namelistValue, other.namelistValue) && Objects.equals(namelistStatus, other.namelistStatus) && Objects.equals(createBy, other.createBy) && Objects.equals(typeWay, other.typeWay) && Objects.equals(expireTime, other.expireTime) && Objects.equals(namelistType, other.namelistType) && Objects.equals(namelistDim, other.namelistDim) && Objects.equals(createTime, other.createTime) && Objects.equals(updateBy, other.updateBy) && Objects.equals(immuneRule, other.immuneRule) && Objects.equals(updateTime, other.updateTime) && Objects.equals(namelistId, other.namelistId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(namelistValue, namelistStatus, createBy, typeWay, expireTime, namelistType, namelistDim, createTime, updateBy, immuneRule, updateTime, namelistId);
    }
}