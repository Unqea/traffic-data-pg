package com.traffic.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 查询参数
 *
 *
 * @since 2022-01-25
 */
@ApiModel("查询参数对象")
public abstract class QueryParam implements Serializable {
    private static final long serialVersionUID = -3263921252635611410L;
    @ApiModelProperty(value = "页码,默认为1", example = "1")
    private Integer current = 1;
    @ApiModelProperty(value = "页大小,默认为10", example = "10")
    private Integer size = 10;

    // <editor-fold defaultstate="collapsed" desc="delombok">
    // </editor-fold>
    // @ApiModelProperty(value = "搜索字符串", example = "")
    // private String keyword;
    public void setCurrent(Integer current) {
        if (current == null || current <= 0) {
            this.current = 1;
        } else {
            this.current = current;
        }
    }

    public void setSize(Integer size) {
        if (size == null || size <= 0) {
            this.size = 10;
        } else {
            this.size = size;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="delombok">
    @SuppressWarnings("all")
    public QueryParam() {}

    @SuppressWarnings("all")
    public Integer getCurrent() {
        return this.current;
    }

    @SuppressWarnings("all")
    public Integer getSize() {
        return this.size;
    }

    @Override
    @SuppressWarnings("all")
    public boolean equals(final Object o) {
        if (o == this)
            return true;
        if (!(o instanceof QueryParam))
            return false;
        final QueryParam other = (QueryParam)o;
        if (!other.canEqual((Object)this))
            return false;
        final Object this$current = this.getCurrent();
        final Object other$current = other.getCurrent();
        if (this$current == null ? other$current != null : !this$current.equals(other$current))
            return false;
        final Object this$size = this.getSize();
        final Object other$size = other.getSize();
        if (this$size == null ? other$size != null : !this$size.equals(other$size))
            return false;
        return true;
    }

    @SuppressWarnings("all")
    protected boolean canEqual(final Object other) {
        return other instanceof QueryParam;
    }

    @Override
    @SuppressWarnings("all")
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $current = this.getCurrent();
        result = result * PRIME + ($current == null ? 43 : $current.hashCode());
        final Object $size = this.getSize();
        result = result * PRIME + ($size == null ? 43 : $size.hashCode());
        return result;
    }

    @Override
    @SuppressWarnings("all")
    public String toString() {
        return "QueryParam(current=" + this.getCurrent() + ", size=" + this.getSize() + ")";
    }
    // </editor-fold>
}
