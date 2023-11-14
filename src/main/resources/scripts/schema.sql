DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id         BIGINT NOT NULL AUTO_INCREMENT,
    email      VARCHAR(255),
    first_name VARCHAR(255),
    last_name  VARCHAR(255),
    password   VARCHAR(255),
    PRIMARY KEY (id)
) ENGINE = InnoDB;

ALTER TABLE users
    ADD CONSTRAINT UK_6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email);

DROP TABLE IF EXISTS food;

CREATE TABLE food
(
    id               INT PRIMARY KEY,
    food_group       VARCHAR(255),
    short_descrip    VARCHAR(255),
    descrip          TEXT,
    common_name      VARCHAR(255),
    mfg_name         VARCHAR(255),
    scientific_name  VARCHAR(255),
    energy_kcal      DOUBLE,
    protein_g        DOUBLE,
    fat_g            DOUBLE,
    carb_g           DOUBLE,
    sugar_g          DOUBLE,
    fiber_g          DOUBLE,
    vitA_mcg         DOUBLE,
    vitB6_mg         DOUBLE,
    vitB12_mcg       DOUBLE,
    vitC_mg          DOUBLE,
    vitE_mg          DOUBLE,
    folate_mcg       DOUBLE,
    niacin_mg        DOUBLE,
    riboflavin_mg    DOUBLE,
    thiamin_mg       DOUBLE,
    calcium_mg       DOUBLE,
    copper_mcg       DOUBLE,
    iron_mg          DOUBLE,
    magnesium_mg     DOUBLE,
    manganese_mg     DOUBLE,
    phosphorus_mg    DOUBLE,
    selenium_mcg     DOUBLE,
    zinc_mg          DOUBLE,
    vitA_USRDA       DOUBLE,
    vitB6_USRDA      DOUBLE,
    vitB12_USRDA     DOUBLE,
    vitC_USRDA       DOUBLE,
    vitE_USRDA       DOUBLE,
    folate_USRDA     DOUBLE,
    niacin_USRDA     DOUBLE,
    riboflavin_USRDA DOUBLE,
    thiamin_USRDA    DOUBLE,
    calcium_USRDA    DOUBLE,
    copper_USRDA     DOUBLE,
    magnesium_USRDA  DOUBLE,
    phosphorus_USRDA DOUBLE,
    selenium_USRDA   DOUBLE,
    zinc_USRDA       DOUBLE
);
