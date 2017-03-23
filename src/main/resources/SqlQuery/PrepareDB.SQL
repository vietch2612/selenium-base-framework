-- DELETE Category
DELETE FROM category WHERE name LIKE 'Automation%';

-- DELETE Manufacture
DELETE FROM manufacturer WHERE name LIKE 'UPDATED%' OR name LIKE 'AUTO%';

-- DELETE BRAND
DELETE FROM brand WHERE name LIKE 'UPDATED%' OR name LIKE 'AUTO%';
