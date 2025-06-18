
#!/bin/bash

# Define source (frontend) and backup directories
SRC_DIR="/c/Users/maddi/Documents/Sprint-1/New folder/online-banking-1/frontend"
BACKUP_DIR="/c/Users/maddi/Documents/Sprint-1/New folder/online-banking-1/frontend/scripts/backups"

# Create timestamp
TIMESTAMP=$(date +"%Y-%m-%d_%H-%M")
BACKUP_FILE="$BACKUP_DIR/frontend_backup_$TIMESTAMP.tar.gz"

# Create the backup (excluding the backup directory itself)
tar --exclude="$BACKUP_DIR" -czf "$BACKUP_FILE" -C "$SRC_DIR" .

# Output result
echo "✅ Backup completed: $BACKUP_FILE at $TIMESTAMP"
