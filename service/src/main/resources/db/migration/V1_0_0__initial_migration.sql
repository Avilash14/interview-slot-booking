create table booking_history (
                                 id bigint not null auto_increment,
                                 created_at datetime,
                                 updated_at datetime,
                                 booking_reference varchar(255) not null,
                                 interviewee_email varchar(255) not null,
                                 interviewee_mobile_no varchar(255) not null,
                                 interviewee_name varchar(255) not null,
                                 interviewee_resume_url varchar(255) not null,
                                 status varchar(255),
                                 slot_id bigint,
                                 primary key (id)
);

create table slot (
                      id bigint not null auto_increment,
                      created_at datetime,
                      updated_at datetime,
                      end_time TIME,
                      interview_date DATE,
                      slot_tracking_code varchar(255) not null,
                      start_time TIME,
                      status varchar(255),
                      primary key (id)
);

alter table booking_history
    add constraint UK_ekc6rm63xf50k17nrpnsdmnd5 unique (booking_reference);

alter table slot
    add constraint UK_2p7hcqq3kb4uid6rx7mot3xci unique (slot_tracking_code);

alter table booking_history
    add constraint FKg25vc36vcqd3g4159pvbsfdrv
        foreign key (slot_id)
            references slot (id);