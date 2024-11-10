CREATE TABLE IF NOT EXISTS Contracts
(
    "id"            UUID PRIMARY KEY,
    "external_id"   UUID,
    "status"        VARCHAR(255) NOT NULL,
    "key"           VARCHAR(255) NOT NULL UNIQUE,
    "area"          VARCHAR(255) NOT NULL,
    "product"       VARCHAR(255) NOT NULL,
    "contract_date" TIMESTAMP    NOT NULL,
    "created_on"    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    "created_by"    VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Signers
(
    "id"          UUID PRIMARY KEY,
    "external_id" UUID,
    "name"        VARCHAR(255) NOT NULL,
    "email"       VARCHAR(255) NOT NULL,
    "created_on"  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    "created_by"  VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Signatures
(
    "id"          UUID PRIMARY KEY,
    "contract_id" UUID         NOT NULL REFERENCES Contracts ("id") ON DELETE CASCADE,
    "signer_id"   UUID         NOT NULL REFERENCES Signers ("id") ON DELETE CASCADE,
    "external_id" UUID,
    "sign_as"     VARCHAR(255) NOT NULL,
    "signed"      BOOLEAN      NOT NULL,
    "signed_date" TIMESTAMP,
    "created_on"  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    "created_by"  VARCHAR(255) NOT NULL,
    CONSTRAINT "unique_contract_signer" UNIQUE ("contract_id", "signer_id")
);

INSERT INTO Contracts ("id", "status", "key", "area", "product", "contract_date", "created_by")
VALUES ('550e8400-e29b-41d4-a716-446655440000', 'pending', 'SP-001', 'sales', 'software_sale',
        '2023-10-01 10:00:00', 'artur.dornelles@gmail.com'),
       ('550e8400-e29b-41d4-a716-446655440001', 'pending', 'SP-002', 'sales', 'software_sale', '2023-10-02 11:00:00',
        'artur.dornelles@gmail.com'),
       ('550e8400-e29b-41d4-a716-446655440003', 'pending', 'SP-003', 'sales', 'software_sale', '2023-10-04 13:00:00',
        'artur.dornelles@gmail.com');

INSERT INTO Signers ("id", "name", "email", "created_by")
VALUES ('123e4567-e89b-12d3-a456-426614174000', 'Alice', 'alice.brandao@icloud.com', 'artur.dornelles@gmail.com'),
       ('123e4567-e89b-12d3-a456-426614174001', 'Artur', 'artur.dornelles@gmail.com', 'artur.dornelles@gmail.com');

INSERT INTO Signatures ("id", "contract_id", "signer_id", "sign_as", "signed", "created_by")
VALUES ('123e4567-e89b-12d3-a456-426614174002', '550e8400-e29b-41d4-a716-446655440000',
        '123e4567-e89b-12d3-a456-426614174000', 'party', FALSE, 'artur.dornelles@gmail.com'),
       ('123e4567-e89b-12d3-a456-426614174003', '550e8400-e29b-41d4-a716-446655440001',
        '123e4567-e89b-12d3-a456-426614174001', 'party', FALSE, 'artur.dornelles@gmail.com');