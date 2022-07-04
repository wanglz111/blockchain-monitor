package com.xjtlu.monitor.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @TableName api_scan_result
 */
@TableName(value ="api_scan_result")
@Data
public class ApiScanResult implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Object id;

    /**
     *
     */
    private Object blockNumber;

    /**
     *
     */
    private Object timeStamp;

    /**
     *
     */
    private String hash;

    /**
     *
     */
    private String fromAddress;

    /**
     *
     */
    private String toAddress;

    /**
     *
     */
    private Object value;

    /**
     *
     */
    private String input;

    /**
     *
     */
    private Object chainId;

    /**
     *
     */
    private String chainName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ApiScanResult other = (ApiScanResult) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getBlockNumber() == null ? other.getBlockNumber() == null : this.getBlockNumber().equals(other.getBlockNumber()))
            && (this.getTimeStamp() == null ? other.getTimeStamp() == null : this.getTimeStamp().equals(other.getTimeStamp()))
            && (this.getHash() == null ? other.getHash() == null : this.getHash().equals(other.getHash()))
            && (this.getFromAddress() == null ? other.getFromAddress() == null : this.getFromAddress().equals(other.getFromAddress()))
            && (this.getToAddress() == null ? other.getToAddress() == null : this.getToAddress().equals(other.getToAddress()))
            && (this.getValue() == null ? other.getValue() == null : this.getValue().equals(other.getValue()))
            && (this.getInput() == null ? other.getInput() == null : this.getInput().equals(other.getInput()))
            && (this.getChainId() == null ? other.getChainId() == null : this.getChainId().equals(other.getChainId()))
            && (this.getChainName() == null ? other.getChainName() == null : this.getChainName().equals(other.getChainName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getBlockNumber() == null) ? 0 : getBlockNumber().hashCode());
        result = prime * result + ((getTimeStamp() == null) ? 0 : getTimeStamp().hashCode());
        result = prime * result + ((getHash() == null) ? 0 : getHash().hashCode());
        result = prime * result + ((getFromAddress() == null) ? 0 : getFromAddress().hashCode());
        result = prime * result + ((getToAddress() == null) ? 0 : getToAddress().hashCode());
        result = prime * result + ((getValue() == null) ? 0 : getValue().hashCode());
        result = prime * result + ((getInput() == null) ? 0 : getInput().hashCode());
        result = prime * result + ((getChainId() == null) ? 0 : getChainId().hashCode());
        result = prime * result + ((getChainName() == null) ? 0 : getChainName().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", blockNumber=").append(blockNumber);
        sb.append(", timeStamp=").append(timeStamp);
        sb.append(", hash=").append(hash);
        sb.append(", fromAddress=").append(fromAddress);
        sb.append(", toAddress=").append(toAddress);
        sb.append(", value=").append(value);
        sb.append(", input=").append(input);
        sb.append(", chainId=").append(chainId);
        sb.append(", chainName=").append(chainName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
