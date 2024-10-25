
CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255),
    password VARCHAR(255),
    email VARCHAR(255),
    role_id INTEGER REFERENCES roles(id) ON DELETE SET NULL
);

CREATE TABLE equipment_categories (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE equipments (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    category_id INTEGER REFERENCES equipment_categories(id) ON DELETE SET NULL
);

CREATE TABLE user_equipments (
    id SERIAL PRIMARY KEY,
    equipment_id INTEGER REFERENCES equipments(id) ON DELETE CASCADE,
    user_id INTEGER REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE comments (
    id SERIAL PRIMARY KEY,
    comment VARCHAR(100) NOT NULL,
    user_id INTEGER REFERENCES users(id) ON DELETE SET NULL
);

CREATE TABLE ingredient_categories (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);
CREATE TABLE recipe_categories (
                                   id SERIAL PRIMARY KEY,
                                   name VARCHAR(255),
                                   slug VARCHAR(255)
);

CREATE TABLE recipes (
                         id SERIAL PRIMARY KEY,
                         title VARCHAR(255),
                         сontent TEXT,
                         isPublished BOOLEAN,
                         slug VARCHAR(255),
                         author_id INTEGER REFERENCES users(id) ON DELETE SET NULL,
                         category_id INTEGER REFERENCES recipe_categories(id) ON DELETE SET NULL
);

CREATE TABLE abstract_ingredients (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    description TEXT,
    ingredient_category_id INTEGER REFERENCES ingredient_categories(id) ON DELETE SET NULL
);

CREATE TABLE base_ingredients (
    id SERIAL PRIMARY KEY,
    abstract_ingredient_id INTEGER REFERENCES abstract_ingredients(id) ON DELETE CASCADE,
    recipe_id INTEGER REFERENCES recipes(id) ON DELETE CASCADE
);




CREATE TABLE user_ingredients (
    id SERIAL PRIMARY KEY,
    quantity INTEGER,
    user_description TEXT,
    recipe_id INTEGER REFERENCES recipes(id) ON DELETE CASCADE,
    abstract_ingredient_id INTEGER REFERENCES abstract_ingredients(id) ON DELETE SET NULL
);

INSERT INTO equipment_categories (name) VALUES
    ('Electronics'),
    ('Furniture'),
    ('Appliances'),
    ('Tools');

INSERT INTO ingredient_categories (name) VALUES
    ('Dairy'),
    ('Vegetables'),
    ('Spices'),
    ('Grains');

INSERT INTO recipe_categories (name, slug) VALUES
    ('Appetizers', 'appetizers'),
    ('Main Courses', 'main-courses'),
    ('Desserts', 'desserts'),
    ('Beverages', 'beverages');


INSERT INTO roles  (name) VALUES
    ('admin'),
    ('user');
