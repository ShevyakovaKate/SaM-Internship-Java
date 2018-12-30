INSERT INTO GIFT_CERTIFICATE (gc_name, gc_description, gc_price, gc_date_of_creation, gc_date_of_modification, gc_duration)
VALUES (  'Helicopter Flight', 'Give the gift of flight with an Introductory Helicopter Discovery Flight from Canyon State Aero!' ||
 ' From pristine deserts to rocky canyons, from glistening lakes to snow-dusted peaks,' ||
  ' Arizona is an incredible place sightsee by helicopter.',
	150, now() , now() , 60);

INSERT INTO GIFT_CERTIFICATE ( gc_name, gc_description, gc_price, gc_date_of_creation, gc_date_of_modification, gc_duration)
VALUES ( 'Horse riding', 'For that special occasion why not give a Lavant House Stables gift voucher? ' ||
 'You can choose whatever you would like it for (lessons, activity, clothing, etc) or just select a specific value.' ||
  ' Valid for 12 months.',
	300, now(), now(), 356);

INSERT INTO GIFT_CERTIFICATE ( gc_name, gc_description, gc_price, gc_date_of_creation, gc_date_of_modification, gc_duration)
VALUES ( '"Selfie Park" visiting', 'Selfie Park - the world of giant sweets, cartoon characters and unusual locations.' ||
 ' If you want to have fun, get to a fantastic country and take a lot of original photos,' ||
  ' then get a certificate in a selfie park and get a charge of positive, mimic and dozens of juicy shots!',
	20, now(), now(), 90);

INSERT INTO GIFT_CERTIFICATE ( gc_name, gc_description, gc_price, gc_date_of_creation, gc_date_of_modification, gc_duration)
VALUES ( 'Services of the sports complex "Logoisk"', 'In winter, there is a temptation to wrap yourself in a blanket' ||
 ' and sit down to watch TV shows. But it is much better to gather friends, find a warm ski suit and go to Logoisk for impressions.' ||
  ' Here, active entertainment on every square meter, and the spirit of fun can be inhaled along with the frosty air!',
	100, now(), now(), 90);

INSERT INTO GIFT_CERTIFICATE ( gc_name, gc_description, gc_price, gc_date_of_creation, gc_date_of_modification, gc_duration)
VALUES ( 'Master class of italian kitchen.', 'Italian cuisine is popular all over the world.' ||
 ' They attract with exquisite aromas and are remembered for incredible tastes. ' ||
  'With a certificate for an on-site master class, you’ll make an Italian surprise in your own kitchen for your loved one:' ||
   ' he will learn recipes and learn how to cook three traditional dishes from Italy!',
	54, now(), now(), 60);

INSERT INTO GIFT_CERTIFICATE ( gc_name, gc_description, gc_price, gc_date_of_creation, gc_date_of_modification, gc_duration)
VALUES ( 'ATV riding', 'Riding an ATV will delight lovers of extreme sports and speed,' ||
 ' and scenic routes and an instructor’s accompaniment will make a walk through the forest as interesting and safe as possible.',
	60, now(), now(), 180);

INSERT INTO GIFT_CERTIFICATE ( gc_name, gc_description, gc_price, gc_date_of_creation, gc_date_of_modification, gc_duration)
VALUES ( 'Quest performance "1408. Hotel with ghosts"', 'So what can happen in a typical hotel room? ' ||
 'Rumor has it that in 1408 no one lasted more than an hour. It''s time to check what is happening there. ' ||
  'Gather a company and go to the quest-performance. Of course, if you are not afraid to meet with ghosts face to face.',
	29.99, now(), now(), 90);


INSERT INTO GIFT_CERTIFICATE ( gc_name, gc_description, gc_price, gc_date_of_creation, gc_date_of_modification, gc_duration)
VALUES ( 'SPA ritual for the body', 'The most beautiful moments you always want to share with a loved one, right? Especially if these are moments of pleasure and small joys! Give a certificate for the SPA-program to your soulmate and share with each other several hours of genuine enjoyment.',
	45, now(), now(), 90);

INSERT INTO GIFT_TAG (gt_name) VALUES ( 'master classes');
INSERT INTO GIFT_TAG (gt_name) VALUES ( 'sport');
INSERT INTO GIFT_TAG (gt_name) VALUES ( 'romantic');
INSERT INTO GIFT_TAG (gt_name) VALUES ( 'beaty and health');
INSERT INTO GIFT_TAG (gt_name) VALUES ( 'quests');
INSERT INTO GIFT_TAG (gt_name) VALUES ( 'active rest');
INSERT INTO GIFT_TAG (gt_name) VALUES ( 'animals');

INSERT INTO GIFT_CERTIFICATE_HAS_TAG (gc_id, gt_id) VALUES (1, 2);
INSERT INTO GIFT_CERTIFICATE_HAS_TAG (gc_id, gt_id) VALUES (1, 6);
INSERT INTO GIFT_CERTIFICATE_HAS_TAG (gc_id, gt_id) VALUES (2, 2);
INSERT INTO GIFT_CERTIFICATE_HAS_TAG (gc_id, gt_id) VALUES (2, 6);
INSERT INTO GIFT_CERTIFICATE_HAS_TAG (gc_id, gt_id) VALUES (2, 7);
INSERT INTO GIFT_CERTIFICATE_HAS_TAG (gc_id, gt_id) VALUES (5, 1);
INSERT INTO GIFT_CERTIFICATE_HAS_TAG (gc_id, gt_id) VALUES (3, 6);
INSERT INTO GIFT_CERTIFICATE_HAS_TAG (gc_id, gt_id) VALUES (4, 6);
INSERT INTO GIFT_CERTIFICATE_HAS_TAG (gc_id, gt_id) VALUES (6, 6);
INSERT INTO GIFT_CERTIFICATE_HAS_TAG (gc_id, gt_id) VALUES (4, 2);
INSERT INTO GIFT_CERTIFICATE_HAS_TAG (gc_id, gt_id) VALUES (6, 2);
INSERT INTO GIFT_CERTIFICATE_HAS_TAG (gc_id, gt_id) VALUES (7, 5);
INSERT INTO GIFT_CERTIFICATE_HAS_TAG (gc_id, gt_id) VALUES (8, 3);

INSERT INTO ROLE (name) VALUES ('ADMIN');
INSERT INTO ROLE (name) VALUES ('USER');

INSERT INTO "USER" (login, email, password, role_id) VALUES ('Oleg','oleg@mail.ru', '$2a$04$6yKU2chbrm6OmscUJ8pUR.x4oYrJE2KOwreOZpm4ySLjgGCt29MDy', 1); --пароль - oleg--
INSERT INTO "USER" (login, email, password, role_id) VALUES ('Katya', 'katya@mail.ru', '$2a$04$n9fSnrOtumycVAHr8YtZseUn449CczMRGJzc48fyMKl904l5.c3/C', 2); --пароль - katya--

INSERT INTO PAY_STATUS (status_name) VALUES ('WAIT');
INSERT INTO PAY_STATUS (status_name) VALUES ('PAID');

INSERT INTO "ORDER" (gc_id, user_id, pay_status_id, datetime) VALUES (1, 2, 1, now());
INSERT INTO "ORDER" (gc_id, user_id, pay_status_id, datetime) VALUES (2, 1, 1, now());


