# 06-Espera_wait

## Preguntes teòriques

1. **Per què s’atura l’execució al cap d’un temps?**
   L'execució s'atura perquè els assistents estan en un bucle infinit on fan reserves i cancel·lacions de manera aleatòria. A més, hi ha una espera aleatòria de fins a 1 segon entre cada acció, cosa que fa que l'execució es prolongui indefinidament.

2. **Què passaria si en lloc de una probabilitat de 50%-50% fora de 70%(ferReserva)-30%(cancel·lar)? I si foren al revés les probabilitats?**
   - **Codi modificat per a 70%-30%:**
     ```java
     int accio = random.nextInt(10); // 0-6: ferReserva, 7-9: cancel·lar
     if (accio < 7) {
         esdeveniment.ferReserva(this);
     } else {
         esdeveniment.cancelaReserva(this);
     }
     ```
     **Sortida esperada:** Hi hauria més reserves que cancel·lacions, de manera que les places disponibles es reduirien més ràpidament i els assistents haurien d'esperar més temps per fer reserves.

   - **Codi modificat per a 30%-70%:**
     ```java
     int accio = random.nextInt(10); // 0-2: ferReserva, 3-9: cancel·lar
     if (accio < 3) {
         esdeveniment.ferReserva(this);
     } else {
         esdeveniment.cancelaReserva(this);
     }
     ```
     **Sortida esperada:** Hi hauria més cancel·lacions que reserves, de manera que les places disponibles augmentarien més ràpidament i els assistents podrien fer reserves amb més facilitat.

3. **Perquè creus que fa falta la llista i no valdria només amb una variable sencera de reserves?**
   La llista és necessària perquè permet gestionar quins assistents han fet reserves i quins no. Només amb una variable sencera no podríem saber quins assistents tenen reserves actives i, per tant, no podríem gestionar correctament les cancel·lacions. La llista ens permet verificar si un assistent concret té una reserva abans de cancel·lar-la.
