CREATE TABLE IF NOT EXISTS category
(
    category_id   bigint       NOT NULL GENERATED ALWAYS AS IDENTITY,
    category_name varchar(255) NOT NULL,
    catalog_type  varchar(255) NOT NULL,
    PRIMARY KEY (category_id)
);

CREATE TABLE IF NOT EXISTS size
(
    size_id    bigint       NOT NULL GENERATED ALWAYS AS IDENTITY,
    size_value varchar(255) NOT NULL,
    PRIMARY KEY (size_id)
);

CREATE TABLE IF NOT EXISTS category_size
(
    category_id bigint REFERENCES category (category_id),
    size_id     bigint REFERENCES size (size_id)
);

CREATE TABLE IF NOT EXISTS company
(
    company_id   bigint       NOT NULL GENERATED ALWAYS AS IDENTITY,
    company_name varchar(255) NOT NULL,
    PRIMARY KEY (company_id)
);

CREATE TABLE IF NOT EXISTS sale
(
    sale_id    bigint  NOT NULL GENERATED ALWAYS AS IDENTITY,
    sale_value numeric NOT NULL,
    PRIMARY KEY (sale_id)
);

CREATE TABLE IF NOT EXISTS product
(
    product_id   bigint       NOT NULL GENERATED ALWAYS AS IDENTITY,
    category_id  bigint,
    company_id   bigint,
    sale_id      bigint,
    price        numeric      NOT NULL,
    currency     varchar(255) NOT NULL,
    color        varchar(255) NOT NULL,
    product_name varchar(255) NOT NULL,
    description  varchar(255) NOT NULL,
    male         varchar(255) NOT NULL,
    PRIMARY KEY (product_id),
    FOREIGN KEY (category_id)
        REFERENCES category (category_id),
    FOREIGN KEY (company_id)
        REFERENCES company (company_id),
    FOREIGN KEY (sale_id)
        REFERENCES sale (sale_id)
);

CREATE TABLE IF NOT EXISTS product_size
(
    product_id bigint REFERENCES product (product_id),
    size_id    bigint REFERENCES size (size_id)
);

CREATE TABLE IF NOT EXISTS review
(
    review_id    bigint  NOT NULL GENERATED ALWAYS AS IDENTITY,
    product_id   bigint,
    review_value varchar NOT NULL,
    PRIMARY KEY (review_id),
    FOREIGN KEY (product_id)
        REFERENCES product (product_id)
);

CREATE TABLE IF NOT EXISTS usr
(
    user_id  bigint       NOT NULL GENERATED ALWAYS AS IDENTITY,
    username varchar(50)  NOT NULL UNIQUE,
    password varchar(255) NOT NULL,
    role     varchar(20)  NOT NULL,
    PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS product_statistics
(
    product_id    bigint NOT NULL,
    product_views bigint,
    CONSTRAINT FK_product_id FOREIGN KEY (product_id)
        REFERENCES product (product_id)
)