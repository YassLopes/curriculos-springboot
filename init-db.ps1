Write-Host "Waiting for PostgreSQL to start..."
while (-not (Test-NetConnection -ComputerName localhost -Port 5432 -InformationLevel Quiet)) {
    Start-Sleep -Seconds 1
}
Write-Host "PostgreSQL is ready!" 