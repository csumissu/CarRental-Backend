insert into t_car (id, deleted, version, daily_rent_price, model, status)
values (1, false, 0, 100.00, 'BMW 650', 1);

insert into t_order (id, car_id, user_id, deleted, version, booked_at, booked_days,
    daily_rent_price, returned_at, total_price)
values (1, 1, 1, false, 0, now(), 2, 100.00, null, 200.00);