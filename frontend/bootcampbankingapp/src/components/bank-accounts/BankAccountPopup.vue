<template>
    <main>
        <h1>Vue Popups</h1>
        <button>Open Popup</button>
        <Popup>
            <h2>My Popup</h2>
        </Popup>
    </main>
</template>

<script>
import Popup from '/components.BankAccountsTable'

export default ({
    setup() {
        return {
            Popup
        }
    },
})
</script>
