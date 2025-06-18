#!/bin/bash

# Get the absolute path to the directory where this script is located
SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
BACKUP_SCRIPT="$SCRIPT_DIR/backup.sh"

# Check if backup.sh exists
if [ ! -f "$BACKUP_SCRIPT" ]; then
  echo "❌ backup.sh not found at: $BACKUP_SCRIPT"
  exit 1
fi

while true; do
  CURRENT_TIME=$(date +"%H:%M")
  TARGET_TIME="10:00"

  if [ "$CURRENT_TIME" == "$TARGET_TIME" ]; then
    echo "✅ Running backup at $CURRENT_TIME"
    bash "$BACKUP_SCRIPT"
    echo "✅ Backup done at $(date)"
    
    # Sleep for more than 1 minute to avoid multiple triggers
    sleep 61
  else
    sleep 30
  fi
done

