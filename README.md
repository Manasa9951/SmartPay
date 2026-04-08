#  SmartPay — Utility Bill Generator

> A Core Java console application that digitizes electricity and water billing using progressive tax slabs and object-oriented design principles.

---

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Project Structure](#project-structure)
- [Pricing Slabs](#pricing-slabs)
- [How It Works](#how-it-works)
- [Getting Started](#getting-started)
- [Sample Output](#sample-output)
- [Concepts Demonstrated](#concepts-demonstrated)
- [Future Improvements](#future-improvements)

---

## Overview

**SmartPay** is a municipality-grade utility bill generator built with plain Java (no external libraries). It calculates electricity/water bills based on meter readings, applies India's progressive slab-rate pricing to encourage resource conservation, adds 18% GST, and prints a formatted digital receipt to the console.

The program runs in a loop accepting multiple customer entries until the user types `Exit`.

---

## Features

- ✅ **Progressive slab billing** — higher consumption is charged at higher rates
- ✅ **Input validation** — catches blank names, non-numeric input, negative values, and reversed meter readings
- ✅ **Interface-driven design** — `Billable` interface with `calculateTotal()` method
- ✅ **18% GST** applied automatically on the base charge
- ✅ **Formatted digital receipt** printed using box-drawing characters
- ✅ **Multi-customer loop** — process unlimited customers in one session; type `Exit` to quit

---

## Project Structure

```
SmartPay/
│
├── SmartPay.java        # Main entry point — input loop, validation, receipt trigger
│   ├── interface Billable          # Contract: calculateTotal()
│   ├── class UtilityBill           # Implements Billable; slab logic + receipt
│   └── class SmartPay              # main() — scanner loop & orchestration
│
└── README.md
```

---

## Pricing Slabs

| Slab | Units Consumed | Rate per Unit | Cumulative Max |
|------|---------------|---------------|----------------|
| 1    | 0 – 100       | ₹ 5.00        | ₹ 500          |
| 2    | 101 – 300     | ₹ 8.00        | ₹ 2,100        |
| 3    | Above 300     | ₹ 12.00       | Unlimited       |

> Billing is **cumulative** — each slab rate applies only to units within that band, not to the entire consumption.

**Example — 350 units:**
```
Slab 1:   100 units × ₹5.00  =  ₹  500.00
Slab 2:   200 units × ₹8.00  =  ₹1,600.00
Slab 3:    50 units × ₹12.00 =  ₹  600.00
                              ─────────────
Base Charge                  =  ₹2,700.00
GST (18%)                    =  ₹  486.00
                              ─────────────
FINAL TOTAL                  =  ₹3,186.00
```

---

## How It Works

```
START
  │
  ▼
Read Customer Name ──► "Exit"? ──► END
  │
  ▼
Read Previous & Current Meter Readings
  │
  ▼
Validate Inputs
  ├─ Blank name?           → Error, retry
  ├─ Non-numeric reading?  → Error, retry
  ├─ Negative value?       → Error, retry
  └─ Previous > Current?   → Error, retry
  │
  ▼
Units Consumed = Current − Previous
  │
  ▼
Apply Slab Pricing (calculateCharge)
  │
  ▼
Add 18% GST (calculateTotal via Billable)
  │
  ▼
Print Digital Receipt
  │
  └──────────────────────► Loop back to top
```

---

## Getting Started

### Prerequisites

- Java JDK 8 or higher
- Any terminal / command prompt

### Compile

```bash
javac SmartPay.java
```

### Run

```bash
java SmartPay
```

### Usage

```
Enter Customer Name (or 'Exit' to quit): Priya Sharma
Enter Previous Meter Reading (units): 200
Enter Current  Meter Reading (units): 550
```

Type `Exit` when prompted for a customer name to end the session.

---

## Sample Output

```
╔══════════════════════════════════════════════╗
║     SMARTPAY – Utility Bill Generator        ║
║  Type 'Exit' at Customer Name to quit.       ║
╚══════════════════════════════════════════════╝

Enter Customer Name (or 'Exit' to quit): Priya Sharma
Enter Previous Meter Reading (units): 200
Enter Current  Meter Reading (units): 550

╔═════════════════════════════════════════════╗
║          ⚡  SMARTPAY DIGITAL RECEIPT  ⚡       ║
╠═════════════════════════════════════════════╣
║  Customer Name   : Priya Sharma             ║
║  Units Consumed  : 350                      ║
╠═════════════════════════════════════════════╣
║  Base Charge     : ₹2700.00                 ║
║  GST (18%)       : ₹486.00                  ║
╠═════════════════════════════════════════════╣
║  *** FINAL TOTAL : ₹3186.00                 ║
╚═════════════════════════════════════════════╝
```

---

## Concepts Demonstrated

| Concept | Where Used |
|---|---|
| Interface & implementation | `Billable` → `UtilityBill.calculateTotal()` |
| Encapsulation | `private` fields in `UtilityBill`, exposed via methods |
| `if-else` slab logic | `calculateCharge()` in `UtilityBill` |
| Exception handling | `NumberFormatException` on meter input |
| Input validation | Blank check, negative check, reversed-reading check |
| Scanner & loops | `while(true)` loop with `break` on `"Exit"` |
| Formatted console output | `System.out.printf` with box-drawing characters |

---

## Future Improvements

- [ ] Add water billing as a separate slab structure
- [ ] Export receipts to a `.txt` or `.pdf` file
- [ ] Persist customer data using file I/O or a database
- [ ] Add a GUI using JavaFX or Swing
- [ ] Unit tests with JUnit 5
- [ ] Support multiple utility types (electricity, water, gas) via polymorphism

---

## License

This project is open source and available under the [MIT License](LICENSE).

---

<p align="center">Built with ☕ Java · Made for SmartPay Municipality Billing</p>
