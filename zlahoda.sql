-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Час створення: Квт 20 2024 р., 23:18
-- Версія сервера: 10.4.32-MariaDB
-- Версія PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База даних: `zlahoda1`
--

-- --------------------------------------------------------

--
-- Структура таблиці `category`
--

CREATE TABLE IF NOT EXISTS `category` (
  `category_number` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(50) NOT NULL,
  PRIMARY KEY (`category_number`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп даних таблиці `category`
--

INSERT INTO `category` (`category_number`, `category_name`) VALUES
(1, 'Fruits, vegetables'),
(2, 'Meat'),
(3, 'Fish'),
(4, 'Sausages and meat products'),
(5, 'Cheese'),
(6, 'Bread and bakery products'),
(7, 'Dairy products and eggs'),
(8, 'Groceries'),
(9, 'Canned goods, sauces, spices'),
(10, 'Frozen products'),
(11, 'Sweets'),
(12, 'Snacks'),
(13, 'Coffee, tea'),
(14, 'Beverages'),
(15, 'Alcohol'),
(16, 'Hygiene and beauty'),
(17, 'For home'),
(18, 'Baby products'),
(19, 'For pets'),
(20, 'Gardening products');

-- --------------------------------------------------------

--
-- Структура таблиці `check`
--

CREATE TABLE IF NOT EXISTS `check` (
  `check_number` varchar(10) NOT NULL,
  `id_employee` varchar(10) NOT NULL,
  `card_number` varchar(13) DEFAULT NULL,
  `print_date` datetime NOT NULL,
  `sum_total` decimal(13,4) NOT NULL,
  `vat` decimal(13,4) NOT NULL,
  PRIMARY KEY (`check_number`),
  KEY `check_employee` (`id_employee`),
  KEY `check_card` (`card_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Структура таблиці `customer_card`
--

CREATE TABLE IF NOT EXISTS `customer_card` (
  `card_number` varchar(13) NOT NULL,
  `cust_surname` varchar(50) NOT NULL,
  `cust_name` varchar(50) NOT NULL,
  `cust_patronymic` varchar(50) DEFAULT NULL,
  `phone_number` varchar(13) NOT NULL,
  `city` varchar(50) DEFAULT NULL,
  `street` varchar(50) DEFAULT NULL,
  `zip_code` varchar(9) DEFAULT NULL,
  `percent` int(11) NOT NULL,
  PRIMARY KEY (`card_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп даних таблиці `customer_card`
--

INSERT INTO `customer_card` (`card_number`, `cust_surname`, `cust_name`, `cust_patronymic`, `phone_number`, `city`, `street`, `zip_code`, `percent`) VALUES
('8fsewfoe8awd8', 'Cumberbatch', 'Benedict', NULL, '02303030393', 'London', NULL, NULL, 50),
('efew8ffgiaqug', 'Freeman', 'Martin', 'John Christopher', '02365423578', NULL, NULL, NULL, 40);

-- --------------------------------------------------------

--
-- Структура таблиці `employee`
--

CREATE TABLE IF NOT EXISTS `employee` (
  `id_employee` varchar(10) NOT NULL,
  `empl_surname` varchar(50) NOT NULL,
  `empl_name` varchar(50) NOT NULL,
  `empl_patronymic` varchar(50) DEFAULT NULL,
  `empl_role` varchar(10) NOT NULL,
  `salary` decimal(13,4) NOT NULL,
  `date_of_birth` date NOT NULL,
  `date_of_start` date NOT NULL,
  `phone_number` varchar(13) NOT NULL,
  `city` varchar(50) NOT NULL,
  `street` varchar(50) NOT NULL,
  `zip_code` varchar(9) NOT NULL,
  `password` varchar(64) NOT NULL,
  PRIMARY KEY (`id_employee`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп даних таблиці `employee`
--

INSERT INTO `employee` (`id_employee`, `empl_surname`, `empl_name`, `empl_patronymic`, `empl_role`, `salary`, `date_of_birth`, `date_of_start`, `phone_number`, `city`, `street`, `zip_code`, `password`) VALUES
('ksid8di7s0', 'Hibskyi', 'Vladyslav', 'Ihorovych', 'Manager', 15000.0000, '2005-07-10', '2024-03-01', '0988487880', 'Kremenets', 'Microrayon', '39005', 'hibskyivladyslavihorovych'),
('ksid8di7s7', 'Triukhan', 'Tetiana', NULL, 'Cashier', 6000.0000, '2005-01-17', '2024-03-20', '0931424561', 'Lubny', 'Travneva', '37005', 'triukhantetiana');

-- --------------------------------------------------------

--
-- Структура таблиці `product`
--

CREATE TABLE IF NOT EXISTS `product` (
  `id_product` int(11) NOT NULL AUTO_INCREMENT,
  `category_number` int(11) NOT NULL,
  `product_name` varchar(50) NOT NULL,
  `characteristics` varchar(100) NOT NULL,
  PRIMARY KEY (`id_product`),
  KEY `product_category` (`category_number`)
) ENGINE=InnoDB AUTO_INCREMENT=328 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп даних таблиці `product`
--

INSERT INTO `product` (`id_product`, `category_number`, `product_name`, `characteristics`) VALUES
(101, 1, 'Avocado', 'Creamy texture, Hass variety, from Mexico'),
(102, 1, 'Sweet potato', 'Orange-fleshed, rich in nutrients, locally grown'),
(103, 1, 'Potato', 'Versatile cooking staple, Russet variety, local farm'),
(104, 1, 'Tomato', 'Juicy, vine-ripened, plum variety, greenhouse'),
(105, 1, 'Cucumber', 'Refreshing, English variety, greenhouse'),
(106, 1, 'Carrot', 'Sweet, crunchy, organic, local farm'),
(107, 1, 'Bell pepper', 'Sweet, colorful, red variety, greenhouse'),
(108, 1, 'Radish', 'Crunchy, spicy, red globe variety, local farm'),
(109, 1, 'Cabbage', 'Crisp, green, Napa variety, locally sourced'),
(110, 1, 'Broccoli', 'Nutrient-rich, green, organic, local farm'),
(111, 1, 'Onion', 'Aromatic, yellow, Spanish variety, local farm'),
(112, 1, 'Leek', 'Mild, flavorful, organic, local farm'),
(113, 1, 'Salad mix \"Puchok-Svizhachok\"', 'Fresh assortment of greens, locally sourced'),
(114, 1, 'Apple', 'Crisp, sweet, Fuji variety, orchard'),
(115, 1, 'Orange', 'Juicy, Valencia variety, from California'),
(116, 1, 'Lemon', 'Tangy, Meyer variety, from Florida'),
(117, 1, 'Kiwi', 'Sweet, green, Zespri variety, imported'),
(118, 1, 'Grapes', 'Juicy, seedless, red globe variety, vineyard'),
(119, 1, 'Coconut', 'Sweet, organic, whole, imported'),
(120, 1, 'Strawberries', 'Juicy, sweet, organic, local farm'),
(121, 1, 'Blueberries', 'Sweet, antioxidant-rich, organic, local farm'),
(122, 2, 'Chicken breast fillet', 'Lean, boneless, 500g pack'),
(123, 2, 'Chicken drumstick', 'Juicy, flavorful, 1kg pack'),
(124, 2, 'Chicken wings', 'Crispy, party-perfect, 800g pack'),
(125, 2, 'Chicken strips', 'Tender, versatile, 400g pack'),
(126, 2, 'Chicken mince', 'Lean, ground, 500g pack'),
(127, 2, 'Turkey steak', 'Tender, lean, 2 pieces'),
(128, 2, 'Pork loin', 'Tender, succulent, 600g pack'),
(129, 2, 'Pork escalope', 'Thinly sliced, 300g pack'),
(130, 2, 'Pork ribs', 'Meaty, slow-cooked, 1kg pack'),
(131, 2, 'Pork shashlik', 'Marinated, 800g pack'),
(132, 2, 'Beef mince', 'Versatile, 500g pack'),
(133, 3, 'Herring', 'Smoked, traditional, Baltic delicacy, 200g pack'),
(134, 3, 'Trout steak', 'Fresh, boneless, premium quality, 2 pieces'),
(135, 3, 'Dorado', 'Mild, flaky, Mediterranean favorite, 1 fish'),
(136, 3, 'Hake', 'Firm, white flesh, versatile, 300g fillet'),
(137, 3, 'Shrimp', 'Juicy, shell-on, Gulf variety, 250g pack'),
(138, 3, 'Lightly salted salmon', 'Delicately cured, Norwegian origin, 150g pack'),
(139, 3, 'Lightly salted trout', 'Tender, artisanal, river-caught, 200g pack'),
(140, 3, 'Salmon caviar', 'Premium, rich flavor, Russian origin, 100g jar'),
(141, 3, 'Cleaned squid', 'Tender rings, ready-to-cook, 300g pack'),
(142, 3, 'Crab sticks', 'Imitation, surimi-based, seafood alternative, 250g pack'),
(143, 3, 'Seafood cocktail', 'Mixed, chilled, ready-to-serve, 400g pack'),
(144, 4, 'Sausage assortment', 'Variety of sliced sausages, 400g pack'),
(145, 4, 'Salami', 'Spicy, cured, Italian classic, 200g pack'),
(146, 4, 'Ham', 'Smoked, thinly sliced, deli favorite, 300g pack'),
(147, 4, 'Pork belly', 'Slow-cooked, savory, traditional, 500g pack'),
(148, 4, 'Hamon', 'Dry-cured, Spanish specialty, 250g pack'),
(149, 4, 'Bacon', 'Smoked, crispy, breakfast staple, 200g pack'),
(150, 4, 'Sausages', 'Juicy, grilled, picnic essential, 8 sausages'),
(151, 4, 'Sausage links', 'Savory, bite-sized, barbecue treat, 12 links'),
(152, 4, 'Mini sausages', 'Flavorful, cocktail-sized, party snack, 20 sausages'),
(153, 5, 'Cream cheese', 'Smooth, spreadable, versatile, 200g pack'),
(154, 5, 'Mozzarella cheese', 'Mild, stretchy, pizza essential, 250g pack'),
(155, 5, 'Feta cheese', 'Tangy, crumbly, Greek specialty, 150g pack'),
(156, 5, 'Processed cheese', 'Creamy, meltable, sandwich-friendly, 200g pack'),
(157, 5, 'Edam cheese', 'Semi-hard, mild, Dutch classic, 300g block'),
(158, 5, 'Mascarpone cheese', 'Rich, creamy, Italian dessert staple, 250g tub'),
(159, 5, 'Cheddar cheese', 'Sharp, aged, versatile, 200g block'),
(160, 5, 'Camembert cheese', 'Creamy, soft, French delicacy, 150g wheel'),
(161, 5, 'Brinza cheese', 'Salty, crumbly, Eastern European, 200g block'),
(162, 5, 'Toast cheese', 'Meltable, savory, perfect for sandwiches, 250g pack'),
(163, 6, 'Whole grain baguette', 'Crispy crust, nutty flavor, 400g'),
(164, 6, 'Buckwheat baguette', 'Dense, rustic, gluten-free, 350g'),
(165, 6, 'Lavash', 'Thin, flexible, perfect for wraps, 250g'),
(166, 6, 'White bread', 'Soft, fluffy, classic, 500g'),
(167, 6, 'Black bread', 'Dense, hearty, traditional Eastern European, 600g'),
(168, 6, 'Toast bread', 'Sliced, versatile, ideal for toasting, 700g'),
(169, 6, 'Pampushky', 'Fluffy, Ukrainian garlic bread rolls, 300g'),
(170, 6, 'Chocolate croissant', 'Buttery, flaky, indulgent, 100g'),
(171, 6, 'Cherry donut', 'Glazed, fruity, sweet, 80g'),
(172, 6, 'Raspberry turnover', 'Buttery pastry, tangy filling, 120g'),
(173, 6, 'Poppy seed roll', 'Sweet, nutty, Eastern European, 200g'),
(174, 6, 'Bagels', 'Chewy, ring-shaped, perfect for breakfast, 80g each'),
(175, 7, 'Chicken eggs', 'Fresh, protein-rich, 12 eggs'),
(176, 7, 'Quail eggs', 'Small, delicate, gourmet choice, 18 eggs'),
(177, 7, 'Milk', 'Creamy, nutritious, 1 liter'),
(178, 7, 'Cottage cheese', 'Creamy, curdled, 250g tub'),
(179, 7, 'Kefir', 'Tangy, probiotic, 500ml bottle'),
(180, 7, 'Sour cream', 'Rich, tangy, 200g tub'),
(181, 7, 'Whipping cream', 'Thick, rich, 250ml carton'),
(182, 7, 'Butter', 'Creamy, indulgent, 250g block'),
(183, 7, 'Vanilla yogurt', 'Smooth, creamy, 400g tub'),
(184, 7, 'Yogurt with grains', 'Nutty, satisfying, 200g tub'),
(185, 7, 'Chocolate yogurt', 'Creamy, indulgent, 150g tub'),
(186, 7, 'Glazed cottage cheese \"Zlahoda\"', 'Sweet, creamy, 200g tub'),
(187, 8, 'Farfalle pasta', 'Butterfly-shaped, versatile, 500g pack'),
(188, 8, 'Conchiglie pasta', 'Shell-shaped, captures sauce, 400g pack'),
(189, 8, 'Spaghetti', 'Long, thin, traditional, 1kg pack'),
(190, 8, 'Sunflower oil', 'Light, neutral, 1 liter bottle'),
(191, 8, 'Olive oil', 'Rich, fruity, extra virgin, 500ml bottle'),
(192, 8, 'Flour', 'All-purpose, versatile, 1kg bag'),
(193, 8, 'Buckwheat groats', 'Nutty, gluten-free, 500g pack'),
(194, 8, 'Oat groats', 'Hearty, nutritious, 750g pack'),
(195, 8, 'Rice', 'White, fluffy, long-grain, 1kg pack'),
(196, 8, 'Corn flakes', 'Crispy, golden, 500g box'),
(197, 8, 'Cocoa', 'Rich, chocolatey, 250g pack'),
(198, 8, 'Powdered sugar', 'Fine, sweet, 500g pack'),
(199, 8, 'Sugar', 'Granulated, sweetens drinks, 1kg pack'),
(200, 8, 'Salt', 'Essential seasoning, enhances flavor, 500g pack'),
(201, 9, 'Canned corn', 'Sweet, tender, 400g can'),
(202, 9, 'Canned peas', 'Green, tender, 300g can'),
(203, 9, 'Canned pickles', 'Crunchy, tangy, 500g jar'),
(204, 9, 'Canned tomatoes', 'Juicy, vine-ripened, 400g can'),
(205, 9, 'Tomato paste', 'Thick, rich, 200g tube'),
(206, 9, 'Ketchup', 'Classic, tangy, 500ml bottle'),
(207, 9, 'Mayonnaise', 'Creamy, smooth, 400g jar'),
(208, 9, 'Mustard', 'Spicy, tangy, 200g jar'),
(209, 9, 'Sweet and sour sauce', 'Balanced, versatile, 250ml bottle'),
(210, 9, 'Canned peaches', 'Sweet, juicy, 400g can'),
(211, 9, 'Canned pineapples', 'Tropical, succulent, 500g can'),
(212, 9, 'Raspberry jam', 'Sweet, fruity, 350g jar'),
(213, 9, 'Blackcurrant jam', 'Tangy-sweet, rich, 300g jar'),
(214, 10, 'Vareniki with potatoes', 'Traditional Ukrainian dumplings, 1 kilogram'),
(215, 10, 'Vareniki with cabbage', 'Savory cabbage-filled, 1 kilogram'),
(216, 10, 'Pelmeni with chicken', 'Chicken dumplings, 500 grams'),
(217, 10, 'Chebureki', 'Crispy meat-filled turnovers, 6 pieces'),
(218, 10, 'Chicken nuggets', 'Breaded chicken bites, 300 grams'),
(219, 10, 'Frozen French fries', 'Crispy potato fries, 500 grams'),
(220, 10, 'Pancakes with meat', 'Meat-filled pancakes, 8 pieces'),
(221, 10, 'Cottage cheese pancakes', 'Soft, cheesy, 200 grams'),
(222, 10, 'Frozen vegetable mix', 'Assorted garden vegetables, 1 kilogram'),
(223, 10, 'Frozen berry mix', 'Mixed summer berries, 500 grams'),
(224, 10, 'Puff pastry', 'Flaky pastry sheets, 400 grams'),
(225, 10, 'Lemon-chocolate ice cream', 'Refreshing citrus-chocolate delight, 1 kilogram'),
(226, 10, 'Mango sorbet', 'Tropical mango-flavored sorbet, 500 milliliters'),
(227, 10, 'Strawberry mochi', 'Sweet, chewy strawberry-filled treats, 300 grams'),
(228, 11, '\"Snickers\" bar', 'Classic, chocolate, caramel, pack of 3'),
(229, 11, '\"Bounty\" bar', 'Coconut-filled, chocolate-covered, pack of 2'),
(230, 11, '\"Roshen\" bar', 'Creamy, Ukrainian chocolate, family pack'),
(231, 11, '\"Milka\" milk chocolate with almonds', 'Smooth, Swiss, 100g bar'),
(232, 11, 'Honey cake', 'Layers of honey, nuts, 500g cake'),
(233, 11, 'M&Ms candies', 'Colorful, chocolate, 200g bag'),
(234, 11, 'Brownie cookies', 'Fudgy, chocolatey, 12 pieces'),
(235, 11, 'Kinder \"Surprise\"', 'Chocolate egg, toy surprise, pack of 4'),
(236, 11, '\"Hazelnut\" cookies with condensed milk', 'Crunchy, sweet, 200g pack'),
(237, 11, '\"Crazy Bee\" candies', 'Sweet, fruity, 150g bag'),
(238, 11, '\"Gummy Worms\" candies', 'Chewy, fruity, 250g pack'),
(239, 11, 'Almond truffles', 'Rich, indulgent, 150g box'),
(240, 12, 'Caramel popcorn', 'Sweet and crunchy snack, 200g pack'),
(241, 12, 'Salted roasted almonds', 'Savory, crunchy almonds, 250g pack'),
(242, 12, 'Salted roasted peanuts', 'Classic salty snack, 300g pack'),
(243, 12, 'Salted roasted pistachios', 'Premium salted pistachios, 200g pack'),
(244, 12, 'Sour cream and onion chips', 'Tangy and savory chips, 150g pack'),
(245, 12, 'Lobster-flavored chips', 'Gourmet seafood-inspired chips, 180g pack'),
(246, 12, 'Ridged chili-flavored chips', 'Spicy ridged potato chips, 160g pack'),
(247, 12, 'Nori seaweed chips', 'Crunchy seaweed-flavored chips, 120g pack'),
(248, 12, 'Croutons with herb flavor', 'Herb-infused crunchy croutons, 150g pack'),
(249, 12, 'Mushroom-flavored croutons', 'Umami-rich mushroom croutons, 180g pack'),
(250, 13, 'Black tea', 'Classic and robust, 100g pack'),
(251, 13, 'Green tea', 'Refreshing and antioxidant-rich, 50g pack'),
(252, 13, 'Fruit tea', 'Fragrant and fruity blend, 75g pack'),
(253, 13, 'Ground coffee', 'Aromatic and rich, 250g pack'),
(254, 13, 'Instant coffee', 'Convenient and quick, 200g jar'),
(255, 13, 'Brewed coffee', 'Fresh and aromatic, 500g pack'),
(256, 13, 'Cocoa drink', 'Creamy and indulgent, 300g pack'),
(257, 14, 'Coca-Cola', 'Classic cola drink, 500ml bottle'),
(258, 14, 'Sprite', 'Refreshing lemon-lime soda, 350ml can'),
(259, 14, 'Fanta', 'Fruity orange soda, 330ml can'),
(260, 14, 'Mineral water', 'Still, refreshing, 1 liter bottle'),
(261, 14, 'Sparkling mineral water', 'Bubbly and refreshing, 750ml bottle'),
(262, 14, 'Iced tea', 'Cool and refreshing, 500ml bottle'),
(263, 14, 'Grape juice', 'Sweet and tangy, 1 liter bottle'),
(264, 14, 'Peach juice', 'Juicy and refreshing, 750ml bottle'),
(265, 14, 'Pineapple juice', 'Tropical and tangy, 500ml bottle'),
(266, 14, 'Pomegranate juice', 'Rich and flavorful, 330ml bottle'),
(267, 14, 'Orange juice', 'Freshly squeezed, 1 liter bottle'),
(268, 14, 'Cherry juice', 'Sweet and tart, 250ml bottle'),
(269, 14, 'Kvass', 'Traditional fermented drink, 1 liter bottle'),
(270, 15, 'Red dry wine', 'Full-bodied and dry, 750ml bottle'),
(271, 15, 'Red sweet wine', 'Rich and sweet, 500ml bottle'),
(272, 15, 'White sweet wine', 'Light and sweet, 750ml bottle'),
(273, 15, 'Semi-sweet red wine', 'Balanced sweetness, 700ml bottle'),
(274, 15, 'Beer \"Lvivske\"', 'Classic Ukrainian beer, 500ml bottle'),
(275, 15, 'Somersby apple cider', 'Crisp and refreshing, 330ml bottle'),
(276, 15, 'Somersby forest fruits cider', 'Fruity and vibrant, 330ml bottle'),
(277, 16, 'Liquid soap with honey scent', 'Moisturizing and fragrant, 250ml bottle'),
(278, 16, 'Chocolate-scented shower cream-gel', 'Indulgent and creamy, 200ml tube'),
(279, 16, 'Coconut-scented shower cream-gel', 'Tropical and hydrating, 150ml bottle'),
(280, 16, 'Orange-scented shampoo', 'Refreshing and invigorating, 300ml bottle'),
(281, 16, 'Hair conditioner', 'Nourishing and smoothing, 200ml bottle'),
(282, 16, 'Toothpaste', 'Fresh and minty, 100g tube'),
(283, 16, 'Toothbrush', 'Soft-bristled, ergonomic handle'),
(284, 16, 'Deodorant', 'Long-lasting protection, 50ml roll-on'),
(285, 16, 'Face mask', 'Hydrating and revitalizing, 50ml tube'),
(286, 16, 'Hand cream', 'Moisturizing and fast-absorbing, 75ml tube'),
(287, 16, 'Pineapple-scented body scrub', 'Exfoliating and refreshing, 150g jar'),
(288, 16, 'Peach-scented lip balm', 'Nourishing and fruity, 10g tube'),
(289, 17, 'Dishwashing liquid', 'Effective grease removal, 500ml bottle'),
(290, 17, 'Kitchen sponges', 'Absorbent and durable, pack of 3'),
(291, 17, 'Parchment paper', 'Non-stick baking, 10-meter roll'),
(292, 17, 'Food wrap', 'Versatile and clingy, 30-meter roll'),
(293, 17, 'Plate set', 'Stylish and durable, set of 6'),
(294, 17, 'Baking dish', 'Oven-safe, ceramic, 9x13 inches'),
(295, 17, 'Cup', 'Ceramic, microwave-safe, 300ml capacity'),
(296, 17, 'Candle set', 'Assorted scents, pack of 6'),
(297, 17, 'Laundry detergent', 'Powerful stain remover, 1kg box'),
(298, 17, 'Dish scrubbers', 'Tough on grime, pack of 5'),
(299, 17, 'Glass cleaner', 'Streak-free shine, 500ml spray bottle'),
(300, 17, 'Toilet paper', 'Soft and absorbent, pack of 12 rolls'),
(301, 17, 'Trash bags', 'Strong and tear-resistant, pack of 30'),
(302, 17, 'Batteries', 'Long-lasting, AA, pack of 4'),
(303, 17, 'Super glue', 'Quick-drying, strong bond, 5g tube'),
(304, 18, 'Baby puree apple-pear', 'Nutritious and flavorful, 100g jar'),
(305, 18, 'Baby puree vegetable mix', 'Balanced and healthy, 120g jar'),
(306, 18, 'Baby puree chicken', 'Protein-rich and easy to digest, 150g jar'),
(307, 18, 'Milk porridge', 'Creamy and nourishing, 200g pack'),
(308, 18, 'Strawberry-banana yogurt', 'Smooth and fruity, 150g cup'),
(309, 18, 'Bifidobacteria yogurt', 'Probiotic-rich, aids digestion, 100g cup'),
(310, 18, 'Diapers', 'Absorbent and comfortable, pack of 24'),
(311, 19, 'Cat food chicken', 'Protein-rich, balanced nutrition, 1kg bag'),
(312, 19, 'Cat food beef', 'Tender and flavorful, 500g pouch'),
(313, 19, 'Cat food tuna', 'Omega-3 rich, 200g can'),
(314, 19, 'Cat litter', 'Absorbent and odor-control, 5kg bag'),
(315, 19, 'Dog food chicken', 'High-quality protein source, 2kg bag'),
(316, 19, 'Dog food beef', 'Nutrient-dense, 1.5kg bag'),
(317, 19, 'Pet bowl', 'Durable and dishwasher-safe, stainless steel'),
(318, 19, 'Dry cat food tuna', 'Crunchy and delicious, 800g bag'),
(319, 19, 'Dog training toy', 'Interactive and durable, rubber material'),
(320, 19, 'Dog treats', 'Chewy and savory, 200g bag'),
(321, 19, 'Cat treats', 'Irresistible and nutritious, 150g bag'),
(322, 20, 'Flower substrate', 'Nutrient-rich, promotes healthy growth, 5kg bag'),
(323, 20, 'Gardening tool set', 'Durable and versatile, 7-piece kit'),
(324, 20, 'Cactus', 'Low-maintenance, desert plant, 15cm pot'),
(325, 20, 'Orchid', 'Elegant and exotic, requires indirect light, 20cm pot'),
(326, 20, 'Flower seed kit', 'Assorted blooms, easy to grow, 10 varieties'),
(327, 20, 'Vegetable seed kit', 'Variety of veggies, suitable for gardeners, 8 varieties');

-- --------------------------------------------------------

--
-- Структура таблиці `sale`
--

CREATE TABLE IF NOT EXISTS `sale` (
  `UPC` varchar(12) NOT NULL,
  `check_number` varchar(10) NOT NULL,
  `product_number` int(11) NOT NULL,
  `selling_price` decimal(13,4) NOT NULL,
  PRIMARY KEY (`UPC`,`check_number`),
  KEY `sale_check` (`check_number`),
  KEY `sale_product` (`UPC`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Структура таблиці `store_product`
--

CREATE TABLE IF NOT EXISTS `store_product` (
  `UPC` varchar(12) NOT NULL,
  `UPC_prom` varchar(12) DEFAULT NULL,
  `id_product` int(11) NOT NULL,
  `selling_price` decimal(13,4) NOT NULL,
  `products_number` int(11) NOT NULL,
  `promotional_product` boolean NOT NULL,
  PRIMARY KEY (`UPC`),
  KEY `store_product_prom_product` (`UPC_prom`),
  KEY `product_store_product` (`id_product`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп даних таблиці `store_product`
--

INSERT INTO `store_product` (`UPC`, `UPC_prom`, `id_product`, `selling_price`, `products_number`, `promotional_product`) VALUES
('68gcxe6kusng', NULL, 225, 56.5000, 90, 1),
('s7dhd9s7dd8u', '68gcxe6kusng', 225, 96.5000, 90, 0);

--
-- Обмеження зовнішнього ключа збережених таблиць
--

--
-- Обмеження зовнішнього ключа таблиці `check`
--
ALTER TABLE `check`
  ADD CONSTRAINT `check_card` FOREIGN KEY (`card_number`) REFERENCES `customer_card` (`card_number`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `check_employee` FOREIGN KEY (`id_employee`) REFERENCES `employee` (`id_employee`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Обмеження зовнішнього ключа таблиці `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `product_category` FOREIGN KEY (`category_number`) REFERENCES `category` (`category_number`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Обмеження зовнішнього ключа таблиці `sale`
--
ALTER TABLE `sale`
  ADD CONSTRAINT `sale_check` FOREIGN KEY (`check_number`) REFERENCES `check` (`check_number`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `sale_product` FOREIGN KEY (`UPC`) REFERENCES `store_product` (`UPC`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Обмеження зовнішнього ключа таблиці `store_product`
--
ALTER TABLE `store_product`
  ADD CONSTRAINT `product_store_product` FOREIGN KEY (`id_product`) REFERENCES `product` (`id_product`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `store_product_prom_product` FOREIGN KEY (`UPC_prom`) REFERENCES `store_product` (`UPC`) ON DELETE SET NULL ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
