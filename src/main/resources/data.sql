TRUNCATE TABLE contact_requests RESTART IDENTITY CASCADE;
TRUNCATE TABLE testimonials RESTART IDENTITY CASCADE;
TRUNCATE TABLE news RESTART IDENTITY CASCADE;
TRUNCATE TABLE users RESTART IDENTITY CASCADE;
TRUNCATE TABLE roles RESTART IDENTITY CASCADE;




INSERT INTO roles (name)
VALUES
    ('ADMIN'),
    ('USER');




INSERT INTO users (username, password, email, role, enabled)
VALUES
    (
        'admin',
        'admin123',
        'admin@ecuadorcomparte.dev',
        'ADMIN',
        true
    );




INSERT INTO news
(title, summary, content, category, image_url, published, reading_time_min, created_at)
VALUES
    (
        'Cimientos de esperanza en Ecuador',
        'Proyecto social enfocado en educación y desarrollo comunitario.',
        'Ecuador Comparte continúa fortaleciendo comunidades mediante proyectos educativos y sociales que generan nuevas oportunidades para niños, jóvenes y familias vulnerables.',
        NULL,
        'https://images.adsttc.com/media/images/5ef1/88e7/b357/6529/f500/036b/newsletter/a.jpg?1592887519',
        true,
        5,
        NOW()
    ),
    (
        'Brigadas médicas rurales en comunidades vulnerables',
        'Jornadas de atención médica y acompañamiento social.',
        'Ecuador Comparte realizó nuevas brigadas médicas en sectores rurales con el objetivo de brindar atención preventiva, orientación familiar y apoyo comunitario a cientos de personas.',
        NULL,
        'https://www.fcm.org.co/wp-content/uploads/2021/06/c8585254-c2f3-439d-830e-ca9d730e16ec.jpg',
        true,
        4,
        NOW()

    );




INSERT INTO contact_requests
(full_name, email, phone, subject, city, message, status, created_at)
VALUES
    (
        'Valentina Gómez',
        'valentina.gomez@gmail.com',
        '+573001234567',
        'Información de voluntariado',
        'Quito',
        'Me gustaría participar en las brigadas de educación.',
        'PENDING',
        NOW()
    ),
    (
        'Andrés Martínez',
        'andres.martinez@hotmail.com',
        '+573109876543',
        'Donaciones corporativas',
        'Guayaquil',
        'Nuestra empresa quiere donar insumos médicos.',
        'PENDING',
        NOW()
    ),
    (
        'Camila Torres',
        'camila.torres@outlook.com',
        '+573204567890',
        'Soporte técnico',
        'Cuenca',
        'Tengo dudas sobre cómo inscribirme en la plataforma.',
        'PENDING',
        NOW()
    );




INSERT INTO testimonials
(title, summary, content, author, location, category, image_url, published, created_at)
VALUES
    (
        'Una experiencia transformadora',
        'Mi tiempo como voluntaria educativa.',
        'Ver la sonrisa de los niños cuando logran comprender un tema difícil paga cada minuto invertido. Apoyar a Ecuador Comparte me cambió la vida.',
        'Mariana Herrera',
        'Quito',
        NULL,
        'https://randomuser.me/api/portraits/women/63.jpg',
        true,
        NOW()
    ),
    (
        'Apoyo médico real y cercano',
        'Testimonio de un médico voluntario.',
        'Las brigadas en el sector rural son indispensables. Encontramos familias que llevaban meses sin un chequeo básico. Excelente labor organizativa.',
        'Santiago Díaz',
        'Manabí',
        NULL,
        'https://randomuser.me/api/portraits/men/77.jpg',
        true,
        NOW()
    );