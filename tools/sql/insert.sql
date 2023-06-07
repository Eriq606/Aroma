insert into report values(1, 1000000,  '2023/06/04 11:20:00'::timestamp);
insert into report values(2, 500000,  '2023/06/05 11:20:00'::timestamp);
insert into report values(3, 700000,  '2023/06/06 11:20:00'::timestamp);

INSERT INTO transaction (description, date_transaction, entree, sortie)
VALUES
  ('Achat de semences ylang-ylang', '2023-06-01', 100.00, 0),
  ('Vente d''huile essentielle ylang-ylang', '2023-06-02', 0, 75.50),
  ('Achat d''engrais ylang-ylang', '2023-06-03', 50.25, 0),
  ('Achat de semences géranium', '2023-06-04', 80.00, 0),
  ('Vente d''huile essentielle géranium', '2023-06-05', 0, 120.50),
  ('Achat d''engrais géranium', '2023-06-06', 60.75, 0);

