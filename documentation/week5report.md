# Week 5 report

This week I decided to fully implement my own versions of ArrayList and HashMap. I might have been able to work out how to do everything with just arrays instead of dynamic lists, but 
it seemed like a simpler solution to rely on ArrayList-like functionality. It was a lot of fun implementing DynamicList and CustomHashMap - DynamicList was simple 
enough to figure out, but CustomHashMap required me to think of some interesting stuff - like how to maintain index coherence when you grow the CustomHashMap.

I will probably put all my datastructures into their own package to help with file management. Further, CustomHashMap and DynamicList, while seemingly functional, remain
untested properly, so that will be a task for the next version. Further, they still lack errorhandling for cases like querying negative indices etc. For the next deadline I'll also start experimenting with larger training sets and some promising tricks suggested by Hannu - 
joining together prepositions with words, etc.

Time used: 5 hours.
