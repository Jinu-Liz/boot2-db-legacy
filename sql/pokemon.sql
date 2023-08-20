create table pokemon
(
    pokemon_id int auto_increment
        primary key,
    name       varchar(255) null,
    type       varchar(255) null
);

INSERT INTO pokemon (pokemon_id, name, type) VALUES (121, 'Pikachu', 'ELECTRIC');
INSERT INTO pokemon (pokemon_id, name, type) VALUES (122, 'Bulbasaur', 'GRASS');
INSERT INTO pokemon (pokemon_id, name, type) VALUES (123, 'Charmander', 'FIRM');
INSERT INTO pokemon (pokemon_id, name, type) VALUES (124, 'Squirtle', 'WATER');
INSERT INTO pokemon (pokemon_id, name, type) VALUES (125, 'Butterfree', 'BUG');
INSERT INTO pokemon (pokemon_id, name, type) VALUES (126, 'Rattata', 'NORMAL');
INSERT INTO pokemon (pokemon_id, name, type) VALUES (127, 'Clefairy', 'FAIRY');
INSERT INTO pokemon (pokemon_id, name, type) VALUES (128, 'Arbok', 'POISON');
INSERT INTO pokemon (pokemon_id, name, type) VALUES (129, 'Diglett', 'GROUND');
INSERT INTO pokemon (pokemon_id, name, type) VALUES (130, 'Kadabra', 'PSYCHIC');