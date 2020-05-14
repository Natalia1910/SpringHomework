drop table if exists course
drop table if exists course_students
drop table if exists course_teachers
drop table if exists student
drop table if exists student_courses
drop table if exists teacher
drop table if exists teacher_courses

create table users (
id bigint not null auto_increment,
name varchar(255) not null,
password varchar(255),
role varchar(255) not null,
primary key (id))

alter table users drop index UK_3g1j96g94xpk3lpxl2qbl985x
alter table users add constraint UK_3g1j96g94xpk3lpxl2qbl985x unique (name)

create table course (
id bigint not null auto_increment,
end_date datetime,
name varchar(255),
start_date datetime,
primary key (id));

create table course_students (
course_id bigint not null,
students_student_id bigint not null);

create table course_teachers (
course_id bigint not null,
teachers_id bigint not null);

create table student (
student_id bigint not null auto_increment,
age integer,
name varchar(255),
surname varchar(255),
primary key (student_id));

create table student_courses (
student_student_id bigint not null,
courses_id bigint not null);

create table teacher (
id bigint not null auto_increment,
age integer not null,
name varchar(255),
number varchar(255),
surname varchar(255),
primary key (id));

create table teacher_courses (
teacher_id bigint not null,
courses_id bigint not null);

alter table course_students
add constraint FKq13i3igkhjdm4cikr2f37674r
foreign key (students_student_id) references student (student_id)

alter table course_students
add constraint FKgut5xj4l8sk6hg3l0t2su2pnc
foreign key (course_id) references course (id)

alter table course_teachers
add constraint FKe3n62rwx3uc1yucgkmw6gjkm5
foreign key (teachers_id) references teacher (id)

alter table course_teachers
add constraint FKlmee8ivi6ymoe34wgwknlurpb
foreign key (course_id) references course (id)

alter table student_courses
add constraint FKlwviiijdg10oc2ui4yl7adh1o
foreign key (courses_id) references course (id)

alter table student_courses
add constraint FKdw14sks3g3ds63rbcmow0vgrg
foreign key (student_student_id) references student (student_id)

alter table teacher_courses
add constraint FKb0yh91nc23jyhxnrpmw9c64s1
foreign key (courses_id) references course (id)

alter table teacher_courses
add constraint FK3s01r7lleqy78wd67qstdi74h
foreign key (teacher_id) references teacher (id)