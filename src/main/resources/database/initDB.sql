CREATE TABLE IS NOT EXISTS employees(
    id      INT NOT NULL AUTO_INCREMENT,
    rol     VARCHAR(50) NOT NULL,
    fio     VARCHAR(255) NOT NULL,
    post    VARCHAR(50) NOT NULL,
    status_empl ENUM('ACTIVE', 'BLOCKED')  default 'ACTIVE',
    PRIMARY KEY (id)
)

CREATE TABLE IS NOT EXISTS credentials(
    employee_id INT NOT NULL,
    user_name   VARCHAR(255) NOT NULL,
    passwrd     VARCHAR(255) NOT NULL,
    PRIMARY KEY (employee_id),
    FOREIGN KEY (employee_id) REFERENCES employees (Id)
    )