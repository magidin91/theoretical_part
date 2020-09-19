--GROUP BY
CREATE TABLE Products ( Id SERIAL PRIMARY KEY, ProductName VARCHAR(30) NOT NULL,
Company VARCHAR(20) NOT NULL, ProductCount INT DEFAULT 0);
insert into Products (ProductName, Company, ProductCount) values ('сыр', 'комп1', '1'),
('молоко', 'комп1', '2'), ('сыр', 'комп2', '1'), ('мороженое', 'комп2', '2'), ('вода', 'комп1', '1');
SELECT Company, ProductCount, COUNT(*) AS ModelsCount FROM Products GROUP BY Company, ProductCount;