CREATE TABLE delivery (
	id BIGINT NOT NULL AUTO_INCREMENT,
	client_id BIGINT NOT NULL,
	tax DECIMAL(10,2) NOT NULL,
	status VARCHAR(20) NOT NULL,
	order_date DATETIME NOT NULL,
	due_date DATETIME,

	recipient_name VARCHAR(60) NOT NULL,
	recipient_address VARCHAR(255) NOT NULL,
	recipient_number VARCHAR(30) NOT NULL,
	recipient_complement VARCHAR(60) NOT NULL,
	recipient_district VARCHAR(30) NOT NULL,

	PRIMARY KEY (id)
);

ALTER TABLE delivery ADD CONSTRAINT fk_client_delivery
FOREIGN KEY (client_id) REFERENCES client (id);