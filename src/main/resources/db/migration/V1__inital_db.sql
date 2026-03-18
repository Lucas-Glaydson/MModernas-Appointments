CREATE TABLE "users"(
    "id" UUID NOT NULL,
    "first_name" VARCHAR(255) NOT NULL,
    "last_name" VARCHAR(255) NOT NULL,
    "cpf" VARCHAR(255) NOT NULL,
    "email" VARCHAR(255) NOT NULL,
    "password_hashed" VARCHAR(255) NOT NULL,
    "phone_number" VARCHAR(255) NOT NULL,
    "role" VARCHAR(255) CHECK
    ("role" IN('')) NOT NULL
);

ALTER TABLE
    "users" ADD PRIMARY KEY("id");

CREATE TABLE "appointments"(
    "id" UUID NOT NULL,
    "date" DATE NOT NULL,
    "client_id" UUID NOT NULL,
    "employer_id" UUID NOT NULL,
    "service_id" UUID NOT NULL,
    "status" VARCHAR(255) CHECK
    ("status" IN('')) NOT NULL
);

ALTER TABLE
    "appointments" ADD PRIMARY KEY("id");

CREATE TABLE "services"(
    "id" UUID NOT NULL,
    "name" VARCHAR(255) NOT NULL,
    "description" TEXT NOT NULL,
    "price" FLOAT(53) NULL
);

ALTER TABLE
    "services" ADD PRIMARY KEY("id");

CREATE TABLE "categories"(
    "id" UUID NOT NULL,
    "name" VARCHAR(255) NOT NULL,
    "description" TEXT NOT NULL
);

ALTER TABLE
    "categories" ADD PRIMARY KEY("id");

CREATE TABLE "service_categories"(
    "id" UUID NOT NULL,
    "service_id" UUID NOT NULL,
    "category_id" UUID NOT NULL
);

ALTER TABLE
    "service_categories" ADD PRIMARY KEY("id");

CREATE TABLE "notifications"(
    "id" UUID NOT NULL,
    "user_id" UUID NOT NULL,
    "content" TEXT NOT NULL,
    "date" DATE NOT NULL
);

ALTER TABLE
    "notifications" ADD PRIMARY KEY("id");

CREATE TABLE "payments"(
    "id" UUID NOT NULL,
    "service_id" UUID NOT NULL,
    "appointments_id" UUID NOT NULL,
    "status" VARCHAR(255) CHECK
    ("status" IN('')) NOT NULL,
    "total" FLOAT(53) NOT NULL,
    "timestamp" DATE NOT NULL
);

ALTER TABLE
    "payments" ADD PRIMARY KEY("id");

CREATE TABLE "payment_type"(
    "id" UUID NOT NULL,
    "payment_id" UUID NOT NULL,
    "payment_type" VARCHAR(255) CHECK
    ("payment_type" IN('')) NOT NULL
);

ALTER TABLE "payment_type" ADD PRIMARY KEY("id");

CREATE TABLE "addresses"(
    "id" UUID NOT NULL,
    "user_id" UUID NOT NULL,
    "zip_code" VARCHAR(255) NOT NULL,
    "number" INTEGER NOT NULL,
    "country" VARCHAR(255) NOT NULL,
    "city" VARCHAR(255) NOT NULL,
    "street" BIGINT NOT NULL
);

ALTER TABLE
    "addresses" ADD PRIMARY KEY("id");

ALTER TABLE
    "payments" ADD CONSTRAINT "payments_service_id_foreign" FOREIGN KEY("service_id") REFERENCES "services"("id");

ALTER TABLE
    "addresses" ADD CONSTRAINT "addresses_user_id_foreign" FOREIGN KEY("user_id") REFERENCES "users"("id");

ALTER TABLE
    "payments" ADD CONSTRAINT "payments_appointments_id_foreign" FOREIGN KEY("appointments_id") REFERENCES "appointments"("id");

ALTER TABLE
    "appointments" ADD CONSTRAINT "appointments_service_id_foreign" FOREIGN KEY("service_id") REFERENCES "services"("id");

ALTER TABLE
    "notifications" ADD CONSTRAINT "notifications_user_id_foreign" FOREIGN KEY("user_id") REFERENCES "users"("id");

ALTER TABLE
    "payment_type" ADD CONSTRAINT "payment_type_payment_id_foreign" FOREIGN KEY("payment_id") REFERENCES "payments"("id");

ALTER TABLE
    "service_categories" ADD CONSTRAINT "service_categories_service_id_foreign" FOREIGN KEY("service_id") REFERENCES "services"("id");

ALTER TABLE
    "appointments" ADD CONSTRAINT "appointments_client_id_foreign" FOREIGN KEY("client_id") REFERENCES "users"("id");

ALTER TABLE
    "service_categories" ADD CONSTRAINT "service_categories_category_id_foreign" FOREIGN KEY("category_id") REFERENCES "categories"("id");