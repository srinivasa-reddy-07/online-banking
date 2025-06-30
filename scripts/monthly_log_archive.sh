#!/bin/bash

# === CONFIGURATION ===
LOG_DIR="./logs"                            # Log directory (create it if doesn't exist)
ARCHIVE_BASE="./log-archives"              # Archive base folder

# === TIMESTAMP ===
MONTH_YEAR=$(date +"%Y-%m")
ARCHIVE_DIR="$ARCHIVE_BASE/logs-$MONTH_YEAR"

# === CREATE ARCHIVE FOLDER IF NOT EXISTS ===
mkdir -p "$ARCHIVE_DIR"

# === MOVE LOG FILES ===
find "$LOG_DIR" -type f -name "*.log" -exec mv {} "$ARCHIVE_DIR" \;

# === OPTIONAL: COMPRESS THE ARCHIVE ===
# tar -czf "$ARCHIVE_DIR.tar.gz" -C "$ARCHIVE_BASE" "logs-$MONTH_YEAR"
# rm -r "$ARCHIVE_DIR"  # Uncomment if you want to delete after compression

# === LOG THE ACTION ===
echo "Logs moved to $ARCHIVE_DIR on $(date)" >> "$ARCHIVE_BASE/archive_log.txt"
