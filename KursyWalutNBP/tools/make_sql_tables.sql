DROP SCHEMA ea CASCADE;

CREATE SCHEMA ea;

CREATE TABLE ea.tables (
  id SERIAL PRIMARY KEY,
  date DATE NOT NULL UNIQUE);

CREATE TABLE thb_1 (
  id SERIAL PRIMARY KEY,
  date DATE NOT NULL UNIQUE REFERENCES tables(date) ON DELETE CASCADE,
  exchangerate REAL NULL);

CREATE TABLE ea.usd_1 (
  id SERIAL PRIMARY KEY,
  date DATE NOT NULL UNIQUE REFERENCES tables(date) ON DELETE CASCADE,
  exchangerate REAL NULL);

CREATE TABLE ea.aud_1 (
  id SERIAL PRIMARY KEY,
  date DATE NOT NULL UNIQUE REFERENCES tables(date) ON DELETE CASCADE,
  exchangerate REAL NULL);

CREATE TABLE ea.hkd_1 (
  id SERIAL PRIMARY KEY,
  date DATE NOT NULL UNIQUE REFERENCES tables(date) ON DELETE CASCADE,
  exchangerate REAL NULL);

CREATE TABLE ea.cad_1 (
  id SERIAL PRIMARY KEY,
  date DATE NOT NULL UNIQUE REFERENCES tables(date) ON DELETE CASCADE,
  exchangerate REAL NULL);

CREATE TABLE ea.nzd_1 (
  id SERIAL PRIMARY KEY,
  date DATE NOT NULL UNIQUE REFERENCES tables(date) ON DELETE CASCADE,
  exchangerate REAL NULL);

CREATE TABLE ea.sgd_1 (
  id SERIAL PRIMARY KEY,
  date DATE NOT NULL UNIQUE REFERENCES tables(date) ON DELETE CASCADE,
  exchangerate REAL NULL);

CREATE TABLE ea.eur_1 (
  id SERIAL PRIMARY KEY,
  date DATE NOT NULL UNIQUE REFERENCES tables(date) ON DELETE CASCADE,
  exchangerate REAL NULL);

CREATE TABLE ea.huf_100 (
  id SERIAL PRIMARY KEY,
  date DATE NOT NULL UNIQUE REFERENCES tables(date) ON DELETE CASCADE,
  exchangerate REAL NULL);

CREATE TABLE ea.chf_1 (
  id SERIAL PRIMARY KEY,
  date DATE NOT NULL UNIQUE REFERENCES tables(date) ON DELETE CASCADE,
  exchangerate REAL NULL);

CREATE TABLE ea.gbp_1 (
  id SERIAL PRIMARY KEY,
  date DATE NOT NULL UNIQUE REFERENCES tables(date) ON DELETE CASCADE,
  exchangerate REAL NULL);

CREATE TABLE ea.uah_1 (
  id SERIAL PRIMARY KEY,
  date DATE NOT NULL UNIQUE REFERENCES tables(date) ON DELETE CASCADE,
  exchangerate REAL NULL);

CREATE TABLE ea.jpy_100 (
  id SERIAL PRIMARY KEY,
  date DATE NOT NULL UNIQUE REFERENCES tables(date) ON DELETE CASCADE,
  exchangerate REAL NULL);

CREATE TABLE ea.czk_1 (
  id SERIAL PRIMARY KEY,
  date DATE NOT NULL UNIQUE REFERENCES tables(date) ON DELETE CASCADE,
  exchangerate REAL NULL);

CREATE TABLE ea.dkk_1 (
  id SERIAL PRIMARY KEY,
  date DATE NOT NULL UNIQUE REFERENCES tables(date) ON DELETE CASCADE,
  exchangerate REAL NULL);

CREATE TABLE ea.isk_100 (
  id SERIAL PRIMARY KEY,
  date DATE NOT NULL UNIQUE REFERENCES tables(date) ON DELETE CASCADE,
  exchangerate REAL NULL);

CREATE TABLE ea.nok_1 (
  id SERIAL PRIMARY KEY,
  date DATE NOT NULL UNIQUE REFERENCES tables(date) ON DELETE CASCADE,
  exchangerate REAL NULL);

CREATE TABLE ea.sek_1 (
  id SERIAL PRIMARY KEY,
  date DATE NOT NULL UNIQUE REFERENCES tables(date) ON DELETE CASCADE,
  exchangerate REAL NULL);

CREATE TABLE ea.hrk_1 (
  id SERIAL PRIMARY KEY,
  date DATE NOT NULL UNIQUE REFERENCES tables(date) ON DELETE CASCADE,
  exchangerate REAL NULL);

CREATE TABLE ea.ron_1 (
  id SERIAL PRIMARY KEY,
  date DATE NOT NULL UNIQUE REFERENCES tables(date) ON DELETE CASCADE,
  exchangerate REAL NULL);

CREATE TABLE ea.bgn_1 (
  id SERIAL PRIMARY KEY,
  date DATE NOT NULL UNIQUE REFERENCES tables(date) ON DELETE CASCADE,
  exchangerate REAL NULL);

CREATE TABLE ea.try_1 (
  id SERIAL PRIMARY KEY,
  date DATE NOT NULL UNIQUE REFERENCES tables(date) ON DELETE CASCADE,
  exchangerate REAL NULL);

CREATE TABLE ea.ils_1 (
  id SERIAL PRIMARY KEY,
  date DATE NOT NULL UNIQUE REFERENCES tables(date) ON DELETE CASCADE,
  exchangerate REAL NULL);

CREATE TABLE ea.clp_100 (
  id SERIAL PRIMARY KEY,
  date DATE NOT NULL UNIQUE REFERENCES tables(date) ON DELETE CASCADE,
  exchangerate REAL NULL);

CREATE TABLE ea.php_1 (
  id SERIAL PRIMARY KEY,
  date DATE NOT NULL UNIQUE REFERENCES tables(date) ON DELETE CASCADE,
  exchangerate REAL NULL);

CREATE TABLE ea.mxn_1 (
  id SERIAL PRIMARY KEY,
  date DATE NOT NULL UNIQUE REFERENCES tables(date) ON DELETE CASCADE,
  exchangerate REAL NULL);

CREATE TABLE ea.zar_1 (
  id SERIAL PRIMARY KEY,
  date DATE NOT NULL UNIQUE REFERENCES tables(date) ON DELETE CASCADE,
  exchangerate REAL NULL);

CREATE TABLE ea.brl_1 (
  id SERIAL PRIMARY KEY,
  date DATE NOT NULL UNIQUE REFERENCES tables(date) ON DELETE CASCADE,
  exchangerate REAL NULL);

CREATE TABLE ea.myr_1 (
  id SERIAL PRIMARY KEY,
  date DATE NOT NULL UNIQUE REFERENCES tables(date) ON DELETE CASCADE,
  exchangerate REAL NULL);

CREATE TABLE ea.rub_1 (
  id SERIAL PRIMARY KEY,
  date DATE NOT NULL UNIQUE REFERENCES tables(date) ON DELETE CASCADE,
  exchangerate REAL NULL);

CREATE TABLE ea.idr_10000 (
  id SERIAL PRIMARY KEY,
  date DATE NOT NULL UNIQUE REFERENCES tables(date) ON DELETE CASCADE,
  exchangerate REAL NULL);

CREATE TABLE ea.inr_100 (
  id SERIAL PRIMARY KEY,
  date DATE NOT NULL UNIQUE REFERENCES tables(date) ON DELETE CASCADE,
  exchangerate REAL NULL);

CREATE TABLE ea.krw_100 (
  id SERIAL PRIMARY KEY,
  date DATE NOT NULL UNIQUE REFERENCES tables(date) ON DELETE CASCADE,
  exchangerate REAL NULL);

CREATE TABLE ea.cny_1 (
  id SERIAL PRIMARY KEY,
  date DATE NOT NULL UNIQUE REFERENCES tables(date) ON DELETE CASCADE,
  exchangerate REAL NULL);

CREATE TABLE ea.xdr_1 (
  id SERIAL PRIMARY KEY,
  date DATE NOT NULL UNIQUE REFERENCES tables(date) ON DELETE CASCADE,
  exchangerate REAL NULL);
