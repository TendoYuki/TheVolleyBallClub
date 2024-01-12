<?php include_once("/srv/http/endpoint/config/config.php"); ?>
<script src="/components/navbar/dashboard-navbar.js" defer></script>

<?php
    enum AdminNavbarEntry{
        case members;
        case admins;
        case competitions;
        case trainings;
        case none;

    }
    class AdminNavbar {
        private $active;
        public function __construct($active) {
            $this->active = $active;
        }

        public function display() {
            echo(
                '
                <ul class="admin-navbar">
                    <li '.($this->active==AdminNavbarEntry::trainings ? 'class="active"' : '').'>
                        '.get_public_file("symbols/training-symbol.svg").'
                        <a href="/dashboard/trainings">Entrainements</a>
                    </li>
                    <li '.($this->active==AdminNavbarEntry::competitions ? 'class="active"' : '').'>
                        '.get_public_file("symbols/competition-symbol.svg").'
                        <a href="/dashboard/competitions">Compétitions</a>
                    </li>
                    <li '.($this->active==AdminNavbarEntry::members ? 'class="active"' : '').'>
                        '.get_public_file("symbols/user-symbol.svg").'
                        <a href="/dashboard/members">Membres</a>
                    </li>
                    <li '.($this->active==AdminNavbarEntry::admins ? 'class="active"' : '').'>
                        '.get_public_file("symbols/admin-symbol.svg").'
                        <a href="/dashboard/admins">Administrateurs</a>
                    </li>
                </ul>
                '
            );
        }
    }
?>
