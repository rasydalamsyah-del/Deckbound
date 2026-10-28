# Deckbound

Game kartu (deckbuilder) untuk Android, ditulis dengan **Kotlin** native.

## Status
- [x] Halaman awal: Mulai, Koleksi Kartu, Pengaturan, Keluar
- [ ] Arena pertarungan (energi, damage, blok)
- [ ] Sistem kartu (serang, bertahan, pendukung, power)
- [ ] Level & progres

## Build
APK dibuat otomatis oleh GitHub Actions setiap push ke `main`.
Unduh di tab **Actions** → pilih run terakhir → **Artifacts** → `Deckbound-debug-apk`.

Build lokal: `gradle assembleDebug` (butuh JDK 17 dan Android SDK).
