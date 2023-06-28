import os

# Imposta la directory corrente
directory = os.getcwd()

# Itera attraverso i numeri da 1 a 21
for i in range(1, 22):
    # Crea il vecchio e il nuovo nome del file
    old_name = f"blastwave{i}.png"
    new_name = f"healingwave{i}.png"
    
    # Rinomina il file
    os.rename(os.path.join(directory, old_name), os.path.join(directory, new_name))
    
    print(f"Rinominato il file {old_name} in {new_name}")