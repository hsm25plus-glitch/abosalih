const V="abosalih-9e15981063";const CORE=["./", "index.html", "manifest.webmanifest", "icon-192.png", "icon-512.png", "lib/pdf.min.js", "lib/pdf.worker.min.js"];
self.addEventListener("install",e=>{e.waitUntil(caches.open(V).then(c=>c.addAll(CORE.slice(0,7)).then(()=>Promise.all(CORE.slice(7).map(u=>c.add(u).catch(()=>{}))))).then(()=>self.skipWaiting()))});
self.addEventListener("activate",e=>{e.waitUntil(caches.keys().then(ks=>Promise.all(ks.filter(k=>k!==V).map(k=>caches.delete(k)))).then(()=>self.clients.claim()))});
self.addEventListener("fetch",e=>{const r=e.request;if(r.method!=="GET")return;const u=new URL(r.url);if(u.origin!==location.origin)return;
if(r.mode==="navigate"||u.pathname.endsWith("/index.html")||u.pathname.endsWith("/")){e.respondWith(fetch(r,{cache:"no-store"}).then(x=>{const c=x.clone();caches.open(V).then(k=>k.put("index.html",c));return x}).catch(()=>caches.match("index.html",{ignoreSearch:true})));return}
e.respondWith(caches.match(r,{ignoreSearch:true}).then(m=>m||fetch(r).then(x=>{if(x.ok){const c=x.clone();caches.open(V).then(k=>k.put(r,c))}return x})))});
