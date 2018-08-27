/**
 * Message_t.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package agent.toatech;

public class Message_t  implements java.io.Serializable {
    private java.lang.String app_host;

    private long app_port;

    private java.lang.String app_url;

    private long message_id;

    private java.lang.Long company_id;

    private java.lang.String address;

    private java.lang.String send_to;

    private java.lang.String subject;

    private java.lang.String body;

    public Message_t() {
    }

    public Message_t(
           java.lang.String app_host,
           long app_port,
           java.lang.String app_url,
           long message_id,
           java.lang.Long company_id,
           java.lang.String address,
           java.lang.String send_to,
           java.lang.String subject,
           java.lang.String body) {
           this.app_host = app_host;
           this.app_port = app_port;
           this.app_url = app_url;
           this.message_id = message_id;
           this.company_id = company_id;
           this.address = address;
           this.send_to = send_to;
           this.subject = subject;
           this.body = body;
    }


    /**
     * Gets the app_host value for this Message_t.
     * 
     * @return app_host
     */
    public java.lang.String getApp_host() {
        return app_host;
    }


    /**
     * Sets the app_host value for this Message_t.
     * 
     * @param app_host
     */
    public void setApp_host(java.lang.String app_host) {
        this.app_host = app_host;
    }


    /**
     * Gets the app_port value for this Message_t.
     * 
     * @return app_port
     */
    public long getApp_port() {
        return app_port;
    }


    /**
     * Sets the app_port value for this Message_t.
     * 
     * @param app_port
     */
    public void setApp_port(long app_port) {
        this.app_port = app_port;
    }


    /**
     * Gets the app_url value for this Message_t.
     * 
     * @return app_url
     */
    public java.lang.String getApp_url() {
        return app_url;
    }


    /**
     * Sets the app_url value for this Message_t.
     * 
     * @param app_url
     */
    public void setApp_url(java.lang.String app_url) {
        this.app_url = app_url;
    }


    /**
     * Gets the message_id value for this Message_t.
     * 
     * @return message_id
     */
    public long getMessage_id() {
        return message_id;
    }


    /**
     * Sets the message_id value for this Message_t.
     * 
     * @param message_id
     */
    public void setMessage_id(long message_id) {
        this.message_id = message_id;
    }


    /**
     * Gets the company_id value for this Message_t.
     * 
     * @return company_id
     */
    public java.lang.Long getCompany_id() {
        return company_id;
    }


    /**
     * Sets the company_id value for this Message_t.
     * 
     * @param company_id
     */
    public void setCompany_id(java.lang.Long company_id) {
        this.company_id = company_id;
    }


    /**
     * Gets the address value for this Message_t.
     * 
     * @return address
     */
    public java.lang.String getAddress() {
        return address;
    }


    /**
     * Sets the address value for this Message_t.
     * 
     * @param address
     */
    public void setAddress(java.lang.String address) {
        this.address = address;
    }


    /**
     * Gets the send_to value for this Message_t.
     * 
     * @return send_to
     */
    public java.lang.String getSend_to() {
        return send_to;
    }


    /**
     * Sets the send_to value for this Message_t.
     * 
     * @param send_to
     */
    public void setSend_to(java.lang.String send_to) {
        this.send_to = send_to;
    }


    /**
     * Gets the subject value for this Message_t.
     * 
     * @return subject
     */
    public java.lang.String getSubject() {
        return subject;
    }


    /**
     * Sets the subject value for this Message_t.
     * 
     * @param subject
     */
    public void setSubject(java.lang.String subject) {
        this.subject = subject;
    }


    /**
     * Gets the body value for this Message_t.
     * 
     * @return body
     */
    public java.lang.String getBody() {
        return body;
    }


    /**
     * Sets the body value for this Message_t.
     * 
     * @param body
     */
    public void setBody(java.lang.String body) {
        this.body = body;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Message_t)) return false;
        Message_t other = (Message_t) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.app_host==null && other.getApp_host()==null) || 
             (this.app_host!=null &&
              this.app_host.equals(other.getApp_host()))) &&
            this.app_port == other.getApp_port() &&
            ((this.app_url==null && other.getApp_url()==null) || 
             (this.app_url!=null &&
              this.app_url.equals(other.getApp_url()))) &&
            this.message_id == other.getMessage_id() &&
            ((this.company_id==null && other.getCompany_id()==null) || 
             (this.company_id!=null &&
              this.company_id.equals(other.getCompany_id()))) &&
            ((this.address==null && other.getAddress()==null) || 
             (this.address!=null &&
              this.address.equals(other.getAddress()))) &&
            ((this.send_to==null && other.getSend_to()==null) || 
             (this.send_to!=null &&
              this.send_to.equals(other.getSend_to()))) &&
            ((this.subject==null && other.getSubject()==null) || 
             (this.subject!=null &&
              this.subject.equals(other.getSubject()))) &&
            ((this.body==null && other.getBody()==null) || 
             (this.body!=null &&
              this.body.equals(other.getBody())));
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
        if (getApp_host() != null) {
            _hashCode += getApp_host().hashCode();
        }
        _hashCode += new Long(getApp_port()).hashCode();
        if (getApp_url() != null) {
            _hashCode += getApp_url().hashCode();
        }
        _hashCode += new Long(getMessage_id()).hashCode();
        if (getCompany_id() != null) {
            _hashCode += getCompany_id().hashCode();
        }
        if (getAddress() != null) {
            _hashCode += getAddress().hashCode();
        }
        if (getSend_to() != null) {
            _hashCode += getSend_to().hashCode();
        }
        if (getSubject() != null) {
            _hashCode += getSubject().hashCode();
        }
        if (getBody() != null) {
            _hashCode += getBody().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Message_t.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:toatech:agent", "message_t"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("app_host");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:toatech:agent", "app_host"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("app_port");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:toatech:agent", "app_port"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("app_url");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:toatech:agent", "app_url"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("message_id");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:toatech:agent", "message_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("company_id");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:toatech:agent", "company_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("address");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:toatech:agent", "address"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("send_to");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:toatech:agent", "send_to"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subject");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:toatech:agent", "subject"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("body");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:toatech:agent", "body"));
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

}
