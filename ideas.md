## PokeManager
#### Purpose: let users record the Pokemon they have caught and various details about them

#### Information user can record/see about their pokemon Pokemon:
- Species species
- String nickname
- Move[] moves (has to be a subset of species moveset)
- Integer level
- DateTime time_caught
- String location_caught
- Battle[] Battles
- Integer current_HP
- Integer total_HP (calculated from species and level)
- Image Picture (from species)
- Type[] Types (from species)
- Type[] weak_against (calculated from Types)
- Type[] strong_against  (calculated from Types)

#### Battles model:
- DateTime battle_date
- Species opponent_species
- Integer opponent_level

### Other models I think I would need: (wouldn’t be modifiable by the user)
#### Species model:
- String name
- Image picture
- Move[] moveset
- Type[] types
- Integer baseHP

#### Types model:
- String name
- Type[] strong_against
- Type[] weak_against

#### Moves model:
- String name
- Integer power
- Type type

