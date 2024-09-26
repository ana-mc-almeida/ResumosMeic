var 0..1000: b;
var 0..1000: c;

int: flour = 4000;
int: bananas = 6;
int: sugar = 2000;
int: butter = 500;
int: cocoa = 500;

constraint 250 * b + 200 * c <= flour;
constraint 2 * b <= bananas;
constraint 75 * b + 150 * c <= sugar;
constraint 100 * b + 150 * c <= butter;
constraint 75 * c <= cocoa;

solve maximize b * 40 + c * 45;