drop table PAYMENT cascade constraints;
CREATE TABLE PAYMENT (
	TID	NVARCHAR2(20) PRIMARY KEY  
    ,rsrv_No NUMBER NULL
	,partner_order_id	NVARCHAR2(100)		NULL
	,partner_user_id	NVARCHAR2(100)		NULL
	,payment_method_type	NVARCHAR2(100)		NULL          
	,item_name	NVARCHAR2(100)	NULL	
    ,total NUMBER      
    ,TAX_FREE number
    ,vat number
    ,discount NUMBER
    ,quantity NUMBER	
    ,approved_at TIMESTAMP
);