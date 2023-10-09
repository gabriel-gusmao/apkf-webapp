# apkf-webapp

This is a backend project, still in development, made to improve my skills. I´m working on a system based on the 
administration of the kung fu association in which I am instructor, in Brazil. APKF (Associação Phoenix de Kung Fu) or 
(Phoenix Kung Fu Association) in English.

The graduation system of the association is a little different from the more common belt color change. More pieces of 
clothing changes during the progress of the member. So, these graduation levels are called "phases". And some of the 
phases have levels inside it.

The next stage of this project is to change from JPA to JDBC. It was a recommendation from a friend that is a senior
Java developer.

For the login system, not yet implemented, the idea is to change the level of authorization with the exam approval. The
exam changes the member phase, based on the grade, and the new phase determines the authorization role.
Inside the system we have two levels of authorization. At the fifth phase we are considered instructors, and professors
at the eighth phase. But what kind of restrictions will be applied on the security roles are yet to be decided with the
association administration team.