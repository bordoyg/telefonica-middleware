/**
 * Message_response_t.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package agent.toatech;
import java.lang.StringBuilder;

public class Message_response_t  implements java.io.Serializable {
    private long message_id;

    private java.lang.String status;

    private java.lang.String description;

    private java.lang.String data;

    private java.lang.String external_id;

    private java.lang.String duration;

    private java.lang.String sent;

    public Message_response_t() {
    }

    public Message_response_t(
           long message_id,
           java.lang.String status,
           java.lang.String description,
           java.lang.String data,
           java.lang.String external_id,
           java.lang.String duration,
           java.lang.String sent) {
           this.message_id = message_id;
           this.status = status;
           this.description = description;
           this.data = data;
           this.external_id = external_id;
           this.duration = duration;
           this.sent = sent;
    }


    /**
     * Gets the message_id value for this Message_response_t.
     * 
     * @return message_id
     */
    public long getMessage_id() {
        return message_id;
    }


    /**
     * Sets the message_id value for this Message_response_t.
     * 
     * @param message_id
     */
    public void setMessage_id(long message_id) {
        this.message_id = message_id;
    }


    /**
     * Gets the status value for this Message_response_t.
     * 
     * @return status
     */
    public java.lang.String getStatus() {
        return status;
    }


    /**
     * Sets the status value for this Message_response_t.
     * 
     * @param status
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }


    /**
     * Gets the description value for this Message_response_t.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this Message_response_t.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the data value for this Message_response_t.
     * 
     * @return data
     */
    public java.lang.String getData() {
        return data;
    }


    /**
     * Sets the data value for this Message_response_t.
     * 
     * @param data
     */
    public void setData(java.lang.String data) {
        this.data = data;
    }


    /**
     * Gets the external_id value for this Message_response_t.
     * 
     * @return external_id
     */
    public java.lang.String getExternal_id() {
        return external_id;
    }


    /**
     * Sets the external_id value for this Message_response_t.
     * 
     * @param external_id
     */
    public void setExternal_id(java.lang.String external_id) {
        this.external_id = external_id;
    }


    /**
     * Gets the duration value for this Message_response_t.
     * 
     * @return duration
     */
    public java.lang.String getDuration() {
        return duration;
    }


    /**
     * Sets the duration value for this Message_response_t.
     * 
     * @param duration
     */
    public void setDuration(java.lang.String duration) {
        this.duration = duration;
    }


    /**
     * Gets the sent value for this Message_response_t.
     * 
     * @return sent
     */
    public java.lang.String getSent() {
        return sent;
    }


    /**
     * Sets the sent value for this Message_response_t.
     * 
     * @param sent
     */
    public void setSent(java.lang.String sent) {
        this.sent = sent;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Message_response_t)) return false;
        Message_response_t other = (Message_response_t) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.message_id == other.getMessage_id() &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.data==null && other.getData()==null) || 
             (this.data!=null &&
              this.data.equals(other.getData()))) &&
            ((this.external_id==null && other.getExternal_id()==null) || 
             (this.external_id!=null &&
              this.external_id.equals(other.getExternal_id()))) &&
            ((this.duration==null && other.getDuration()==null) || 
             (this.duration!=null &&
              this.duration.equals(other.getDuration()))) &&
            ((this.sent==null && other.getSent()==null) || 
             (this.sent!=null &&
              this.sent.equals(other.getSent())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        _hashCode += new Long(getMessage_id()).hashCode();
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getData() != null) {
            _hashCode += getData().hashCode();
        }
        if (getExternal_id() != null) {
            _hashCode += getExternal_id().hashCode();
        }
        if (getDuration() != null) {
            _hashCode += getDuration().hashCode();
        }
        if (getSent() != null) {
            _hashCode += getSent().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Message_response_t.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:toatech:agent", "message_response_t"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("message_id");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:toatech:agent", "message_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:toatech:agent", "status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:toatech:agent", "description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("data");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:toatech:agent", "data"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("external_id");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:toatech:agent", "external_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("duration");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:toatech:agent", "duration"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sent");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:toatech:agent", "sent"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(30);
        sb.append("[Message_response_t] = {")
            .append(this.getMessage_id())
            .append(", ").append(this.getStatus())
            .append(", ").append(this.getDescription())
            .append(", ").append(this.getData())
            .append(", ").append(this.getExternal_id())
            .append(", ").append(this.getDuration())
            .append(", ").append(this.getSent())
            .append("}");
        return sb.toString();
    }
}
